function updatePurchaseStatus(purchaseId, status) {

    $("#recipt").html("<span class=\"spinner-border spinner-border-sm\" role=\"status\" aria-hidden=\"true\"></span>");
    $.ajax({
        type: "POST",
        url: "mystore",
        data: {orderId : purchaseId, orderStatus : status},
        success: function(data) {
            if (data == "2") {
                $("#purchase-status").html("<strong>Stato: </strong><i class=\"fa fa-gift\"></i> pronto per il ritiro");
                $("#recipt").html("Notifica avvenuta consegna");
                $("#recipt").attr("onclick", "updatePurchaseStatus(" + purchaseId + ",3);")
            } else if (data == "3") {
                $("#purchase-status").html("<strong>Stato: </strong><i class=\"fa fa-truck\"></i> consegnato");
                $("#recipt").hide();
            }
        }
    });
}