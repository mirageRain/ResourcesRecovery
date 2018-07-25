var myScroll,wx;
$(function(){
	
	var swiper = new Swiper('.swiper-container1', {
		autoplay: 5000,//可选选项，自动滑动
		pagination : '.swiper-pagination1'
	});
	var swiper = new Swiper('.swiper-container2', {
		autoplay: 5000,//可选选项，自动滑动
		pagination : '.swiper-pagination2'
	});

	var swiper = new Swiper('.swiper-container3 .swiper-slide', {
		freeMode:true,
		freeModeFluid:true,
		slidesPerView: 'auto',
		simulateTouch:false/*,
		centeredSlides: true*/
	});

	
	var swiper = new Swiper('.swiper-container4 .swiper-slide', {
		freeMode:true,
		freeModeFluid:true,
		slidesPerView: 'auto',
		simulateTouch:false/*,
		centeredSlides: true*/
	});
	
						
	var swiper = new Swiper('.swiper-container5', {
	    pagination: '.swiper-pagination5',
		slidesPerView: 3.5,
		paginationClickable: true,
		spaceBetween: 10,
		freeMode: true
	});
	
	var swiper = new Swiper('.swiper-container6', {
		pagination: '.swiper-pagination6',
		slidesPerView: 2.5,
		paginationClickable: true,
		spaceBetween: 10,
		freeMode: true
	});


});
