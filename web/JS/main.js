//Made by Zygmunt Kwaśniewicz and Josip Brljevic
$.get("../Save.xml", function (xml, status) {
  var template = [
    '<div class="puce section animation"><div class="row mx-0 px-0 my-1 py-2 m-lg-4 p-lg-3"><h2>',
    "",
    '</h2><div class="col-lg-6 justify-content-center my-auto p-0"><img class="proPho rounded-3" src=',
    "",
    ' alt="Project Image"></div><div class="col-lg-6 p-3 ps-lg-4"><div class="row justify-content-center"><div class="progress p-0 m-2 mb-3"><div class="progress-bar bg-warning progress-bar-striped progress-bar-animated"role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"style="width:',
    "",
    '%"></div></div></div><div class="row"><div class="col-lg-5"><h3>Type of project:</h3></div><div class="d-none d-lg-block col-lg-6"><h3>',
    "",
    '</h3></div><div class="d-lg-none col-lg-6"><h3><span class="tab"></span>',
    "",
    '</h3></div><div class="col-lg-5"><h3>Man hours used:</h3></div><div class="d-none d-lg-block col-lg-6"><h3>',
    "",
    '</h3></div><div class="d-lg-none col-lg-6"><h3><span class="tab"></span>',
    "",
    '</h3></div><div class="col-lg-5"><h3>Expected price:</h3></div><div class="d-none d-lg-block col-lg-6"><h3>',
    "",
    '</h3></div><div class="d-lg-none col-lg-6"><h3><span class="tab"></span>',
    "",
    '</h3></div><div class="col-lg-5"><h3>Estimated total hours:</h3></div><div class="d-none d-lg-block col-lg-6"><h3>',
    "",
    '</h3></div><div class="d-lg-none col-lg-6"><h3><span class="tab"></span>',
    "",
    "</h3></div></div></div></div></div>",
  ];
  var names = $(xml).find("Name");
  var manHours = $(xml).find("TotalHours");
  var estimatedManHours = $(xml).find("ExpectedTotalHours");
  var spentPrices = $(xml).find("Expenses");
  var expectedPrices = $(xml).find("ExpectedExpenses");
  var statuses = $(xml).find("Status");
  for (let i = 0; i < names.length; i++) {
    if ($(statuses[i]).text() == "Ongoing") {
      var ans = "";
      var type = $($(names[i]).parent())
        .parent()[0]
        .tagName.replace("Projects", "");
      template[1] = $(names[i]).text(); //template[1] is the placeholder for the project name
      switch (type) { //template[3] is the placeholder for the image url
        case "Commercial":
          template[3] = "Images/Commercial1.jpg";
          break;
        case "Industrial":
          template[3] = "Images/industrial_building.png";
          break;
        case "Residential":
          template[3] = "Images/Residential_project.png";
          break;
        case "Road":
          template[3] = "Images/Road_construction.png";
          break;
      }
      template[5] =
        (($(manHours[i]).text() * 100) / $(estimatedManHours[i]).text() +
          ($(spentPrices[i]).text() * 100) / $(expectedPrices[i]).text()) /
        2; //percantage
      template[7] = type;
      template[9] = type;
      template[11] = $(manHours[i]).text()  + " Hours";
      template[13] = $(manHours[i]).text()  + " Hours";
      template[15] = $(expectedPrices[i]).text() + " DKK";
      template[17] = $(expectedPrices[i]).text() + " DKK";
      template[19] = $(estimatedManHours[i]).text()  + " Hours";
      template[21] = $(estimatedManHours[i]).text()  + " Hours";

      template.forEach((element) => {
        ans += element;
      });
      $("#hereBeProjects").after(ans);
    }
  }

  //Josip Brljevic
  //checking if there are no displayed projects
  //if there are no projects, it displays a warning message (it adds the class)
  function checkNoProjects() {
    var visibleProjects = $(".puce:visible").length;
    if (visibleProjects === 0) {
      $("#noProjectsMessage").removeClass("d-none");
    } else {
      $("#noProjectsMessage").addClass("d-none");
    }
  }

  //calling the method on render of the webpage
  checkNoProjects();

  //filtering function using the search bar
  function filterProjects(searchTerm) {
    //setting the variable to false
    var projectsFound = false;
    $(".puce").each(function () {
      //finding the project name of each element
      var projectName = $(this).find("h2").text().toLowerCase();
      if (projectName.includes(searchTerm.toLowerCase())) {
        //the element is shown if it matches the search term, and the projectFound variable is set to true
        $(this).show();
        projectsFound = true;
      } else {
        //otherwise it is hidden
        $(this).hide();
      }
    });
    // Show/hide no match message based on the flag
    if (!projectsFound) {
      $(".noProjects").show();
    } else {
      $(".noProjects").hide();
    }
  }
  // Initial display of all projects
  filterProjects("");

  // Event listener for the search input
  $("#form1").on("input", function () {
    var searchValue = $(this).val();
    filterProjects(searchValue);
  });

  // Function to filter projects by type
  function filterByType(type) {
    //setting the variable to false
    var projectsFound = false;
    $(".puce").each(function () {
      //finding the project type of each elemnt
      var projectType1 = $(this).find("h3").eq(0).text().trim(); // Index 7 in the template array
      var projectType2 = $(this).find("h3").eq(1).text().trim(); // Index 9 in the template array
      if (projectType1 === type || projectType2 === type) {
        //show the element if the projectType matches the type argument, also sets the variable to true
        $(this).show();
        projectsFound = true;
      } else {
        $(this).hide();
      }
    });
    // Show/hide no match message based on the flag
    if (!projectsFound) {
      $(".noProjects").show();
    } else {
      $(".noProjects").hide();
    }
  }

  // Event listener for filter buttons
  $(".filter-btn").on("click", function () {
    var type = $(this).data("type");
    var isActive = $(this).hasClass("active");

    // Toggle active class and filter based on the button state
    if (isActive) {
      $(".filter-btn").removeClass("active");
      $(".puce").show();
      $(".noProjects").hide();
    } else {
      $(".filter-btn").removeClass("active");
      $(this).addClass("active");
      filterByType(type);
    }
  });
});
