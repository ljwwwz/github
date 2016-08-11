/**
 * Website: http://git.oschina.net/hbbcs/bootStrap-addTabs
 *
 * Version : 1.0
 *
 * Created by joe on 2016-2-4.
 */


(function($) {

    $.fn.bindExtTab = function(opts) {    
      
       this.optionsParams= $.extend({
            content: '', //直接指定所有页面TABS内容
            close: false, //是否可以关闭
           // monitor: 'body', //监视的区域
            iframeUse: true, //使用iframe还是ajax
            iframeHeight: $(document).height() - 52, //固定TAB中IFRAME高度,根据需要自己修改
            method: 'init',
            //关闭后回调函数
            callback: function () { }
            }, opts || {}
        );
        this.addTab= (function (opts) {
            // debugger;
            var id = opts.id;//'tab_' + 
            //obj.find('.active').removeClass('active');
            $('li[role = "presentation"].active').removeClass('active'); 
            $('div[role = "tabpanel"].active').removeClass('active');
            //debugger;
            //如果TAB不存在，创建一个新的TAB
            if (!$("#" + id)[0]) {
                //创建新TAB的title
                 //是否允许关闭
                var closeFlag=opts.close;
                if(closeFlag==undefined){
                    closeFlag=this.optionsParams.close;
                }
                
                var aOptions={
                        'href': '#' + id,
                        'aria-controls': id,
                        'role': 'tab',
                        'data-toggle': 'tab'
                };
                if(closeFlag){
                    aOptions.class='close-tab_m';
                    aOptions.title='双击可关闭';
                }
                var titleHtml=getTitleHtml(opts.title,closeFlag);
                var title = $('<li>', {
                    'role': 'presentation',
                    'id': 'tab_' + id
                }).append(
                    $('<a>', aOptions).html(titleHtml)
                );
               
                //创建新TAB的内容
                var content = $('<div>', {
                    'class': 'tab-pane',
                    'id': id,
                    'role': 'tabpanel'
                });

                //是否指定TAB内容
                if (opts.content) {
                    content.append(opts.content);
                } else if (this.optionsParams.iframeUse && !opts.ajax) {//没有内容，使用IFRAME打开链接
                    content.append(
                        $('<iframe>', {
                            'class': 'iframeClass',
                            'height': this.optionsParams.iframeHeight,
                            'frameborder': "no",
                            'border': "0",
                           // 'scrolling':"no",
                            'src': opts.url
                        })
                    );
                } else {
                    $.get(opts.url, function (data) {
                        content.append(data);
                    });
                }
                //加入TABS
                $(this).children('.nav-tabs').append(title);
                $(this).children(".tab-content").append(content);
            }

            //激活TAB
            $("#tab_" + id).addClass('active');
            $("#" + id).addClass("active");
            drop($(this));
        });

         this.closeTab= ( function (id) {
            //如果关闭的是当前激活的TAB，激活他的前一个TAB
            if ($(this).find("li.active").attr('id') == "tab_" + id) {
                $("#tab_" + id).prev().addClass('active');
                $("#" + id).prev().addClass('active');
            }
            //关闭TAB
            $("#tab_" + id).remove();
            $("#" + id).remove();
            drop($(this));
            this.optionsParams.callback();
        });
        this.selectTab=( function (id) {
            $("li[role='presentation']").removeClass("active");
            $("div[role='tabpanel']").removeClass("active");
            $("#tab_" + id).addClass('active');
            $("#" + id).addClass('active');
            drop($(this));
            this.optionsParams.callback();
        });
        this.containTab=(function(id){
           var content =  $("#" + id);
            if(content!=undefined&&content.find('iframe:first').length!=0){
                return true;
            }
            return false;
        });
        this.reloadTab=(function (loadParams) {
        	var id=loadParams.id;
        	var newTitle=loadParams.title;
        	var newUrl=loadParams.url;
            var content =  $("#" + id);
            if(content!=undefined){
                var destIframe=content.find('iframe:first');
                if(destIframe!=undefined&&destIframe.length!=0){
                	if(newTitle!=undefined){
                		var destA=$("#tab_" + id).find('a:first');
                		var closeFlag=(destA.hasClass('close-tab_m'));
                		destA.html(getTitleHtml(newTitle,closeFlag));
                	}
                    var destUrl=newUrl;
                    if(destUrl==undefined){
                        destUrl=destIframe.attr('src');
                    }
                    destIframe.attr('src',destUrl);
                }
            }
        });
        this.findTabPage=(function (id) {
            var content =  $("#" + id);
            var destIframe=undefined;
            if(content!=undefined){
                destIframe=content.find('iframe:first');
                return destIframe[0];
            }
            return destIframe!=undefined?destIframe[0]:null;
        });
        

      tabOnResize(this);
      tabDbClick(this); 
       return this;
  };    
  // private function 
    function tabOnResize($obj) {  
        $(window).resize(function () {
            $obj.find('iframe:first').attr('height', $obj.optionsParams.iframeHeight);
            drop($obj);
        });

    }

    function tabDbClick($obj) {  
        $obj.on('dblclick', '.close-tab_m', function () {
        //alert(1);
        var id = $(this).attr("aria-controls");//.prev("a")
        $obj.closeTab(id);
        });

    }
    function getTitleHtml(titleText,closeFlag){
    	 var titleHtml=titleText;
         if(closeFlag){
             titleHtml+='<i class="glyphicon glyphicon-floppy-disk"></i>';
         }
         return titleHtml;
         
    }

    function drop($obj) {
            var element = $obj.find('.nav-tabs');
            //创建下拉标签
            var dropdown = $('<li>', {
                'class': 'dropdown pull-right hide tabdrop'
            }).append(
                $('<a>', {
                    'class': 'dropdown-toggle',
                    'data-toggle': 'dropdown',
                    'href': '#'
                }).append(
                    $('<i>', {'class': "glyphicon glyphicon-align-justify"})
                ).append(
                    $('<b>', {'class': 'caret'})
                )
            ).append(
                $('<ul>', {'class': "dropdown-menu"})
            )

            //检测是否已增加
            if (!$('.tabdrop').html()) {
                dropdown.prependTo(element);
            } else {
                dropdown = element.find('.tabdrop');
            }
            //检测是否有下拉样式
            if (element.parent().is('.tabs-below')) {
                dropdown.addClass('dropup');
            }
            var collection = 0;

            //检查超过一行的标签页
            //debugger;
            element.append(dropdown.find('li'))
                .find('>li')
                .not('.tabdrop')
                .each(function () {
                    if (this.offsetTop > 0 || element.width() - $(this).position().left - $(this).width() < 53) {
                        dropdown.find('ul').append($(this));
                        collection++;
                    }
                });

            //如果有超出的，显示下拉标签
            if (collection > 0) {
                dropdown.removeClass('hide');
                if (dropdown.find('.active').length == 1) {
                    dropdown.addClass('active');
                } else {
                    dropdown.removeClass('active');
                }
            } else {
                dropdown.addClass('hide');
            }
        }
//  ...    
})(jQuery);

