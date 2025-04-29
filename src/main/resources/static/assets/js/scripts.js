/*!
    * Start Bootstrap - SB Admin v7.0.7 (https://startbootstrap.com/template/sb-admin)
    * Copyright 2013-2023 Start Bootstrap
    * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
    */
    // 
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});


$(document).ready(function () {
  const maxHistory = 4;
  const pageTitle = document.title;
  const pagePath = window.location.pathname;

  // Get and update history
  let history = JSON.parse(localStorage.getItem("pageHistory")) || [];
  history = history.filter(item => item.path !== pagePath);
  history.unshift({ title: pageTitle, path: pagePath });
  history = history.slice(0, maxHistory);
  localStorage.setItem("pageHistory", JSON.stringify(history));

  // Render breadcrumb
  let breadcrumbHtml = '';
  history.reverse().forEach((item, index) => {
    if (index === history.length - 1) {
      breadcrumbHtml += `<li class="breadcrumb-item active text-muted" aria-current="page">${item.title}</li>`;
    } else {
      breadcrumbHtml += `<li class="breadcrumb-item"><a class="text-decoration-none text-secondary" href="${item.path}">${item.title}</a></li>`;
    }
  });

  $('.breadcrumb').html(breadcrumbHtml).addClass("border-bottom pb-3");
});


