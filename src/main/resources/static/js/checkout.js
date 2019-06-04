/* JS Document */

/******************************

[Table of Contents]

1. Vars and Inits
2. Set Header
3. Init Search
4. Init Menu
5. Init Quantity


******************************/

$(document).ready(function()
{
	"use strict";

	/* 

	1. Vars and Inits

	*/

	var header = $('.header');
	var hambActive = false;
	var menuActive = false;

	setHeader();

	$(window).on('resize', function()
	{
		setHeader();
	});

	$(document).on('scroll', function()
	{
		setHeader();
	});

	initSearch();
	initMenu();
	initQuantity();

	/* 

	2. Set Header

	*/

	function setHeader()
	{
		if($(window).scrollTop() > 100)
		{
			header.addClass('scrolled');
		}
		else
		{
			header.removeClass('scrolled');
		}
	}

	/* 

	3. Init Search

	*/

	function initSearch()
	{
		if($('.search').length && $('.search_panel').length)
		{
			var search = $('.search');
			var panel = $('.search_panel');

			search.on('click', function()
			{
				panel.toggleClass('active');
			});
		}
	}

	/* 

	4. Init Menu

	*/

	function initMenu()
	{
		if($('.hamburger').length)
		{
			var hamb = $('.hamburger');

			hamb.on('click', function(event)
			{
				event.stopPropagation();

				if(!menuActive)
				{
					openMenu();
					
					$(document).one('click', function cls(e)
					{
						if($(e.target).hasClass('menu_mm'))
						{
							$(document).one('click', cls);
						}
						else
						{
							closeMenu();
						}
					});
				}
				else
				{
					$('.menu').removeClass('active');
					menuActive = false;
				}
			});

			//Handle page menu
			if($('.page_menu_item').length)
			{
				var items = $('.page_menu_item');
				items.each(function()
				{
					var item = $(this);

					item.on('click', function(evt)
					{
						if(item.hasClass('has-children'))
						{
							evt.preventDefault();
							evt.stopPropagation();
							var subItem = item.find('> ul');
						    if(subItem.hasClass('active'))
						    {
						    	subItem.toggleClass('active');
								TweenMax.to(subItem, 0.3, {height:0});
						    }
						    else
						    {
						    	subItem.toggleClass('active');
						    	TweenMax.set(subItem, {height:"auto"});
								TweenMax.from(subItem, 0.3, {height:0});
						    }
						}
						else
						{
							evt.stopPropagation();
						}
					});
				});
			}
		}
	}

	function openMenu()
	{
		var fs = $('.menu');
		fs.addClass('active');
		hambActive = true;
		menuActive = true;
	}

	function closeMenu()
	{
		var fs = $('.menu');
		fs.removeClass('active');
		hambActive = false;
		menuActive = false;
	}

	/* 

	5. Init Quantity

	*/

	function initQuantity()
	{
		// Handle product quantity input
		if($('.product_quantity').length)
		{
			var input = $('#quantity_input');
			var incButton = $('#quantity_inc_button');
			var decButton = $('#quantity_dec_button');

			var originalVal;
			var endVal;

			incButton.on('click', function()
			{
				originalVal = input.val();
				endVal = parseFloat(originalVal) + 1;
				input.val(endVal);
			});

			decButton.on('click', function()
			{
				originalVal = input.val();
				if(originalVal > 0)
				{
					endVal = parseFloat(originalVal) - 1;
					input.val(endVal);
				}
			});
		}
	}

});
function changeStatus(id){
	//var id_order=document.getElementById("id_order").innerText;
	var selected=document.getElementById(id+'id');
	var selectedValue=selected.options[selected.selectedIndex].value;

	// console.log(id);
	// console.log(selectedValue);
	// console.log(selected);

	$.ajax({
		type:'POST',
		url:'/order/status/'+id+'/'+selectedValue,
		success: function() {
			location.reload();
		}
	});
}

function showAddProduct() {
    var items= document.getElementById("items");
    var orders= document.getElementById("orders");
    var producer= document.getElementById("producer_details");
    var item= document.getElementById("item_details");
    var product= document.getElementById("product_details");
    items.style.display = "none";
    orders.style.display = "none";
    producer.style.display = "none";
    item.style.display = "none";
    product.style.display = "contents";
}
function showAddItem() {
    var items= document.getElementById("items");
    var orders= document.getElementById("orders");
    var producer= document.getElementById("producer_details");
    var item= document.getElementById("item_details");
    var product= document.getElementById("product_details");
    items.style.display = "none";
    orders.style.display = "none";
    producer.style.display = "none";
    item.style.display = "contents";
    product.style.display = "none";
}
function showAddProducer() {
    var items= document.getElementById("items");
    var orders= document.getElementById("orders");
    var producer= document.getElementById("producer_details");
    var item= document.getElementById("item_details");
    var product= document.getElementById("product_details");
    items.style.display = "none";
    orders.style.display = "none";
    producer.style.display = "contents";
    item.style.display = "none";
    product.style.display = "none";
}
function showItems() {
    var items= document.getElementById("items");
    var orders= document.getElementById("orders");
    var producer= document.getElementById("producer_details");
    var item= document.getElementById("item_details");
    var product= document.getElementById("product_details");
    items.style.display = "contents";
    orders.style.display = "none";
    producer.style.display = "none";
    item.style.display = "none";
    product.style.display = "none";
}
function showOrders() {
    var items= document.getElementById("items");
    var orders= document.getElementById("orders");
    var producer= document.getElementById("producer_details");
    var item= document.getElementById("item_details");
    var product= document.getElementById("product_details");
    items.style.display = "none";
    orders.style.display = "contents";
    producer.style.display = "none";
    item.style.display = "none";
    product.style.display = "none";
}
// function showAddItem() {
//     var x= document.getElementById("desc_area");
//     var y= document.getElementById("review_area");
//     x.style.display = "none";
//     y.style.display = "block";
// }