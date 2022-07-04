$(document).ready(() => {
    let cart = new Map()
    let coffeeNameMap = new Map()

    $('button.order-button').on('click', e => {
        const coffeeId = $(e.target).attr('data-coffee-id')

        $.ajax({
            url: '/api/orders',
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                orders: [
                    {
                        targetCoffeeId: parseInt(coffeeId),
                        quantity: 1,
                    }
                ]
            }),
            dataType: 'json',
            success: data => {
                $.notify('주문 성공', 'success')
            }
        })
    })

    $('button.cart-order-button').on('click', e => {
        if (cart.size === 0) {
            $.notify('카트가 비어있어요')
            return
        }

        $.ajax({
            url: '/api/orders',
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                orders: Array.from(cart).map(([coffeeId, quantity]) => {
                    return {
                        targetCoffeeId: parseInt(coffeeId),
                        quantity,
                    }
                })
            }),
            dataType: 'json',
            success: data => {
                $.notify('주문 성공', 'success')
                clearCart()
            }
        })
    })

    $('button.cart-clear-button').on('click', e => {
        clearCart()
    })

    $('button.cart-button').on('click', e => {
        const coffeeId = $(e.target).attr('data-coffee-id')
        const coffeeName = $(e.target).attr('data-coffee-name')

        $.notify(`${coffeeId} 장바구니 추가`, 'info')

        if (!coffeeNameMap.has(coffeeName)) {
            coffeeNameMap.set(coffeeId, coffeeName)
        }

        if (!cart.has(coffeeId)) {
            cart.set(coffeeId, 0)
        }

        cart.set(coffeeId, cart.get(coffeeId) + 1)

        drawCart(coffeeNameMap, cart)
    })

    function drawCart(coffeeNameMap, cart) {
        $('#cart').html('')

        let cartHtml = ''

        cart.forEach((value, coffeeId) => {
            cartHtml += `<div> ${coffeeNameMap.get(coffeeId)}: ${value} </div>`
        })

        console.log(cartHtml)
        $('#cart').html(cartHtml)
    }

    function clearCart() {
        cart.clear()
        $('#cart').html('')
    }
})
