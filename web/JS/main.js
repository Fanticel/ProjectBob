$.get("../Save.xml", function (xml, status) {
    var template = ["<div class=\"puce section\"><div class=\"row mx-0 px-0 my-1 py-2 m-lg-4 p-lg-3\"><h2>"
,"","</h2><div class=\"col-lg-6 justify-content-center my-auto p-0\"><img class=\"proPho rounded-3\" src="
,"","alt=\"Project Image\"></div><div class=\"col-lg-6 p-3 ps-lg-4\"><div class=\"row justify-content-center\"><div class=\"progress p-0 m-2 mb-3\"><div class=\"progress-bar bg-warning progress-bar-striped progress-bar-animated\"role=\"progressbar\" aria-valuenow=\"75\" aria-valuemin=\"0\" aria-valuemax=\"100\"style=\"width:"
,"","%\"></div></div></div><div class=\"row\"><div class=\"col-lg-5\"><h3>Type of project:</h3></div><div class=\"d-none d-lg-block col-lg-6\"><h3>"
,"","</h3></div><div class=\"d-lg-none col-lg-6\"><h3><span class=\"tab\"></span>"
,"","</h3></div><div class=\"col-lg-5\"><h3>Man hours used:</h3></div><div class=\"d-none d-lg-block col-lg-6\"><h3>"
,"","</h3></div><div class=\"d-lg-none col-lg-6\"><h3><span class=\"tab\"></span>"
,"","</h3></div><div class=\"col-lg-5\"><h3>Expected price:</h3></div><div class=\"d-none d-lg-block col-lg-6\"><h3>"
,"","</h3></div><div class=\"d-lg-none col-lg-6\"><h3><span class=\"tab\"></span>"
,"","</h3></div><div class=\"col-lg-5\"><h3>Estimated total hours:</h3></div><div class=\"d-none d-lg-block col-lg-6\"><h3>"
,"","</h3></div><div class=\"d-lg-none col-lg-6\"><h3><span class=\"tab\"></span>"
,"","</h3></div></div></div></div></div>"];  
    var names = $(xml).find("Name");
    var manHours = $(xml).find("TotalHours");
    var estimatedManHours = $(xml).find("ExpectedTotalHours");
    var spentPrices = $(xml).find("Expenses");
    var expectedPrices = $(xml).find("ExpectedExpenses");
    var ans = "";
    for (let i = 0; i < names.length; i++) {
        template[1] = $(names[i]).text();
        template[3] //image
        template[5] = (($(manHours[i]).text()*100 / $(estimatedManHours[i]).text()) + ($(spentPrices[i]).text()*100 / $(expectedPrices[i]).text())) / 2//percantage
        template[7] //type1
        template[9] //type2
        template.forEach(element => {
            ans += element;
        });
        $("#hereBeProjects").after(ans);
    }
});