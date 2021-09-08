// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#dataTable').DataTable();
  $('#dataTable1').DataTable();
  $('#dataTable2').DataTable();

  var data = new Date();
  var day = data.getDate();

  if (data.getMonth() < 10) {
    var month = "0" + (data.getMonth() + 1);
  }

  if (data.getDate() < 10) {
    day = "0" + data.getDate();
  }

  var dateString = data.getFullYear() + "-" + month + "-" + day;
  var dateString = dateString.toString();

  $("#choose_date").val(dateString);
});
