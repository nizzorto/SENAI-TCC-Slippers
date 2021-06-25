//barra de pesquisa
$(document).on('click', '.search', () => {
    $('.search-bar').addClass('search-bar-active')
});

$(document).on('click', '.search-cancel', () => {
    $('.search-bar').removeClass('search-bar-active')
});

//For login and sign up
$(document).on('click', '.user,.already-account', () => {
    $('.form').addClass('login-active').removeClass('sign-up-active')
});

$(document).on('click', '.sign-up-btn', () => {
    $('.form').addClass('sign-up-active').removeClass('login-active')
});

$(document).on('click', '.form-cancel', () => {
    $('.form').removeClass('login-active').removeClass('sign-up-active')
});

//Product form

//Slider
$(document).ready(() => {
    $('#adaptive').lightSlider({
        adaptiveHeigh: true,
        auto: true,
        item: 1,
        slideMargin: 0,
        loop: true
    });
})

//Fixar menu ao rolar
$(window).scroll(()=>{
    if($(document).scrollTop() > 50){
        $('.navigation').addClass('fix-nav');
    }
    else{
        $('.navigation').removeClass('fix-nav');
    }
});


