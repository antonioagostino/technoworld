function updatePurchaseStatus(purchaseId, status) {
    $.ajax({
        type: "POST",
        url: "mystore",
        data: {orderId : purchaseId, orderStatus : status},
        success: function(data) {
            if(status != data) {
                if (data == "2") {
                    $("#purchase-status").html("<strong>Stato: </strong><i class=\"fa fa-gift\"></i> pronto per il ritiro");
                    $("#recipt").text("Notifica avvenuta consegna");
                    $("#recipt").attr("onclick", "updatePurchaseStatus(" + purchaseId + ",3);")
                } else if (data == "3") {
                    $("#purchase-status").html("<strong>Stato: </strong><i class=\"fa fa-truck\"></i> consegnato");
                    $("#recipt").hide();
                }
            }
        }
    });
}