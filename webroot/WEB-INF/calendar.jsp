<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  %>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />

  <!-- basic styles -->

  <link href="<%=basePath%>assets/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="<%=basePath%>assets/css/font-awesome.min.css" />

  <!--[if IE 7]>
  <link rel="stylesheet" href="<%=basePath%>assets/css/font-awesome-ie7.min.css" />
  <![endif]-->

  <!-- page specific plugin styles -->

  <link rel="stylesheet" href="<%=basePath%>assets/css/fullcalendar.css" />



  <!-- ace styles -->

  <link rel="stylesheet" href="<%=basePath%>assets/css/ace.min.css" />
  <link rel="stylesheet" href="<%=basePath%>assets/css/ace-rtl.min.css" />
  <link rel="stylesheet" href="<%=basePath%>assets/css/ace-skins.min.css" />

  <!--[if lte IE 8]>
  <link rel="stylesheet" href="<%=basePath%>assets/css/ace-ie.min.css" />
  <![endif]-->

  <!-- inline styles related to this page -->

  <!-- ace settings handler -->

  <script src="<%=basePath%>assets/js/ace-extra.min.js"></script>

  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

  <!--[if lt IE 9]>
  <script src="<%=basePath%>assets/js/html5shiv.js"></script>
  <script src="<%=basePath%>assets/js/respond.min.js"></script>
  <![endif]-->
</head>

<body>
  <div class="page-content">
    <div class="row">
      <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->

        <div class="row">
          <div class="col-sm-9">
            <div class="space"></div>

            <div id="calendar"></div>
          </div>

          <div class="col-sm-3">
            <div class="widget-box transparent">
              <div class="widget-header">
                <h4>添加工作日程</h4>
              </div>

              <div class="widget-body">
                <div class="widget-main no-padding">
                  <div id="external-events">
                    <div class="external-event label-grey" data-class="label-grey">
                      <i class="icon-move"></i>
                      下午2点开会
                    </div>

                    <div class="external-event label-success" data-class="label-success">
                      <i class="icon-move"></i>
                      晚上还有约会
                    </div>

                    <div class="external-event label-danger" data-class="label-danger">
                      <i class="icon-move"></i>
                      明天的工作安排
                    </div>

                    <div class="external-event label-purple" data-class="label-purple">
                      <i class="icon-move"></i>
                      项目要上线
                    </div>

                    <div class="external-event label-yellow" data-class="label-yellow">
                      <i class="icon-move"></i>
                      加快进度
                    </div>

                    <div class="external-event label-pink" data-class="label-pink">
                      <i class="icon-move"></i>
                      好好工作
                    </div>

                    <div class="external-event label-info" data-class="label-info">
                      <i class="icon-move"></i>
                      升值加薪
                    </div>

                    <label>
                      <input type="checkbox" class="ace ace-checkbox" id="drop-remove" />
                      <span class="lbl"> 添加日程后删除该工作项</span>
                    </label>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- PAGE CONTENT ENDS -->
      </div><!-- /.col -->
    </div><!-- /.row -->
  </div><!-- /.page-content -->

<!-- basic scripts -->

<!--[if !IE]> -->



<!-- <![endif]-->

<!--[if IE]>

<![endif]-->

<!--[if !IE]> -->

<script type="text/javascript">
  window.jQuery || document.write("<script src='<%=basePath%>assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
  window.jQuery || document.write("<script src='<%=basePath%>assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

<script type="text/javascript">
  if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="<%=basePath%>assets/js/bootstrap.min.js"></script>
<script src="<%=basePath%>assets/js/typeahead-bs2.min.js"></script>

<!-- page specific plugin scripts -->

<script src="<%=basePath%>assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="<%=basePath%>assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="<%=basePath%>assets/js/fullcalendar.min.en.js"></script>
<script src="<%=basePath%>assets/js/bootbox.min.js"></script>

<!-- ace scripts -->

<script src="<%=basePath%>assets/js/ace-elements.min.js"></script>
<script src="<%=basePath%>assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->

<script type="text/javascript">
  jQuery(function($) {

    /* initialize the external events
     -----------------------------------------------------------------*/

    $('#external-events div.external-event').each(function() {

      // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
      // it doesn't need to have a start or end
      var eventObject = {
        title: $.trim($(this).text()) // use the element's text as the event title
      };

      // store the Event Object in the DOM element so we can get to it later
      $(this).data('eventObject', eventObject);

      // make the event draggable using jQuery UI
      $(this).draggable({
        zIndex: 999,
        revert: true,      // will cause the event to go back to its
        revertDuration: 0  //  original position after the drag
      });

    });




    /* initialize the calendar
     -----------------------------------------------------------------*/

    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();


    var calendar = $('#calendar').fullCalendar({
      buttonText: {
        prev: '<i class="icon-chevron-left"></i>',
        next: '<i class="icon-chevron-right"></i>'
      },

      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'month,agendaWeek,agendaDay'
      },
      events: [
        {
          title: '晚上有几餐',
          start: new Date(y, m, 1),
          className: 'label-important'
        },
        {
          title: '接见领导',
          start: new Date(y, m, d-5),
          end: new Date(y, m, d-2),
          className: 'label-success'
        },
        {
          title: '参加公益活动',
          start: new Date(y, m, d-3, 16, 0),
          allDay: false
        }]
      ,
      editable: true,
      droppable: true, // this allows things to be dropped onto the calendar !!!
      drop: function(date, allDay) { // this function is called when something is dropped

        // retrieve the dropped element's stored Event Object
        var originalEventObject = $(this).data('eventObject');
        var $extraEventClass = $(this).attr('data-class');


        // we need to copy it, so that multiple events don't have a reference to the same object
        var copiedEventObject = $.extend({}, originalEventObject);

        // assign it the date that was reported
        copiedEventObject.start = date;
        copiedEventObject.allDay = allDay;
        if($extraEventClass) copiedEventObject['className'] = [$extraEventClass];

        // render the event on the calendar
        // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
        $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

        // is the "remove after drop" checkbox checked?
        if ($('#drop-remove').is(':checked')) {
          // if so, remove the element from the "Draggable Events" list
          $(this).remove();
        }

      }
      ,
      selectable: true,
      selectHelper: true,
      select: function(start, end, allDay) {

        bootbox.prompt("New Event Title:", function(title) {
          if (title !== null) {
            calendar.fullCalendar('renderEvent',
                    {
                      title: title,
                      start: start,
                      end: end,
                      allDay: allDay
                    },
                    true // make the event "stick"
            );
          }
        });


        calendar.fullCalendar('unselect');

      }
      ,
      eventClick: function(calEvent, jsEvent, view) {

        var form = $("<form class='form-inline'><label>Change event name &nbsp;</label></form>");
        form.append("<input class='middle' autocomplete=off type=text value='" + calEvent.title + "' /> ");
        form.append("<button type='submit' class='btn btn-sm btn-success'><i class='icon-ok'></i> Save</button>");

        var div = bootbox.dialog({
          message: form,

          buttons: {
            "delete" : {
              "label" : "<i class='icon-trash'></i> Delete Event",
              "className" : "btn-sm btn-danger",
              "callback": function() {
                calendar.fullCalendar('removeEvents' , function(ev){
                  return (ev._id == calEvent._id);
                })
              }
            } ,
            "close" : {
              "label" : "<i class='icon-remove'></i> Close",
              "className" : "btn-sm"
            }
          }

        });

        form.on('submit', function(){
          calEvent.title = form.find("input[type=text]").val();
          calendar.fullCalendar('updateEvent', calEvent);
          div.modal("hide");
          return false;
        });


        //console.log(calEvent.id);
        //console.log(jsEvent);
        //console.log(view);

        // change the border color just for fun
        //$(this).css('border-color', 'red');

      }

    });


  })
</script>

</body>
</html>
