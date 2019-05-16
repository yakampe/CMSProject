$(function(){
var toolbarOptions = [ [ {
				'align' : []
			} ], [ 'bold', 'italic', 'underline', 'strike' ], // toggled buttons
			[ 'blockquote', 'code-block' ],

			[ {
				'header' : 1
			}, {
				'header' : 2
			} ], // custom button values
			[ {
				'list' : 'ordered'
			}, {
				'list' : 'bullet'
			} ],

			[ {
				'header' : [ 1, 2, 3, 4, 5, 6, false ]
			} ],

			[ {
				'color' : []
			}, {
				'background' : []
			} ], // dropdown with defaults from theme
			[ 'image', 'link' ], [ 'clean' ] // remove formatting button
			];
			var quill = new Quill("#editor", {
				modules : {
					toolbar : toolbarOptions
				},
				theme : 'snow'
			});
});
function saveForm() {
	$("#content").val($(".ql-editor").get(0).innerHTML);
}