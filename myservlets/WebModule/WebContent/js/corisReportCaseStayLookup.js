	$(document).ready(function(){
		$('#loadingContainer').hide();
		    $('#resultsTable').DataTable( {
		    	"retrieve": true,
		    	"stateSave": true,
		        "lengthMenu": [[100, 50, 25, 10], [100, 50, 25, 10]],
		        "columnDefs": [
    				{ "type": "date", "targets": 2},
    				{ "type": "date", "targets": 3}
				]
		    } );
			$('#resultsTable tbody tr').click(function() {
			    $(this).addClass('bg-light').siblings().removeClass('bg-light');
			});
	
			$('[id^=datetimepicker]').datetimepicker({ 
				format: 'MM/DD/YYYY',			
				icons: {
                    previous: "fa fa-angle-left",
                    next: "fa fa-angle-right"
                }
			});
			changeJudgeComm();
			$('#loadingContainer').hide();
		});
	
		function loadLocation(courtType){
			$('#locnCode').find('option').remove();
			$('#judgeComm').val('');
			if(courtType == "J"){
				$('#locnCode').append('<%= justiceLocationOptions %>');
				$('#locnCode').val('<%= locnCode %>');
			} else if(courtType == "D") {
				$('#locnCode').append('<%= districtLocationOptions %>');
				$('#locnCode').val('<%= locnCode %>');
			}
		}
		
		
		
		
		
		