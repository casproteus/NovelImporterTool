package org.cas.client.resource.international;

/***/
public interface MonthConstant
{
    /* width of title bar in calendar */
     int BAR_HEIGHT = 30;

    /* the maxinum numbers of selecting days in day view model */
    int MAX_DAY_COUNT = 14;
    /* the maxinum numbers of selecting weeks in week view model */
     int MAX_WEEK_COUNT = 6;
    /* the maxinum numbers of selecting days with dragging mouse in day view model */
     int MAX_DAY_MODEL = 8;

    /* keep all selected days */
     int NONE_DAY = 0;
    /* remove all selected days */
     int ALL_DAY = 1;
    /* remove all non-week days */
     int NO_WEEK_DAY = 2;

    /* day view model */
     int DAY_MODEL = 0;
    /* work week view model */
     int WORK_MODEL = 1;
    /* week view model */
     int WEEK_MODEL = 2;
    /* month view model */
     int MONTH_MODEL = 3;

    /* summary */
     String SUMMARY = "Summary";
    /* information for summary */
     String OPTION = "Option";
    /* working day */
     String WORK_DAY = "Working Day";
    /* non-working day */
     String OUT_WORK_DAY = "Non-Working Day";
    /* holiday */
     String HOLIDAY = "Holiday";
    /* add holiday button */
     String ADD_HOLIDAYS = "Add Holidays...";

    /* abbreviation of week day in calendar */
     String WEEK_TITLE[] = {"S", "M", "T", "W", "T", "F", "S"};
    
//	 /* full name of week day */
//     String WEEK_NAME[] =
//     {
//         "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday","Saturday"
//     };
	 	      
	 
	 /* full name of week day */
     String WEEK_NAME[] =
     {
         "星期日", "星期一", "星期二", "星期三", "星期四", "星期五","星期六"
     };
     
    /* abbreviation name of week day */
     String WEEK_SHORT_NAME[] = {"Sun", "Mon", "Tue", "Wed", "Thr", "Fri","Sat"};
    
    //用于弹出日期选择区的组合框
     String SUNDAY    = "星期日";
     String MONDAY    = "星期一";
     String TUESDAY   = "星期二";
     String WEDNESDAY = "星期三";
     String THURSDAY  = "星期四";
     String FRIDAY    = "星期五";
     String SATURDAY  = "星期六";
    
     String [] WEEKDAYS = {SUNDAY,MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY};
    
    /* full month name */
//     String MONTH_NAME[] =
//    {
//        "一月", "二月", "三月", "四月",
//        "五月", "六月", "七月", "八月",
//        "九月", "十月", "十一月", "十二月"
//    };
     
     String MONTH_NAMES[] = 
     {
        "1月", "2月", "3月", "4月",
        "5月", "6月", "7月", "8月",
        "9月", "10月", "11月", "12月"
     };
	 
//    /* abbreviation of month name */
//     String MONTH_SHORT_NAME[] =
//    {
//        "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
//    };
     
     /* abbreviation of month name */
//     String MONTH_SHORT_NAME[] =
//    {
//    	"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"
//    };
     
     
    /* quarter name */
     String[] QUERTER_NAME =
    {
        "The First Quarter", "The Second Quarter", "The Third Quarter", "The Fourth Quarter"
    };
    /* year */
     String YEAR_SUMMARY = "Year";
     
    //以下为农历历法中需要的文字常量
	String TIAN_GAN = "癸甲乙丙丁戊己庚辛壬";
	String DI_ZHI = "亥子丑寅卯辰巳午未申酉戌";
	String NONG_LI_MONTH[] = 
	{
		"正月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "冬月", "腊月"
	};
	String NONG_LI_DATE[] =
	{
		"初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十",
		"十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十",
		"廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十"
	};
	String EMBOLISM = "闰"; 
	String SHENG_XIAO = "猪鼠牛虎兔龙蛇马羊猴鸡狗";
	String SOLAR_TERM[] =
	{
		"小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", 
		"芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", 
		"立冬", "小雪", "大雪", "冬至"
	};
	String CONSTELLATION[] =
	{
		"摩羯座", "宝瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", 
		"狮子座", "处女座", "天秤座", "天蝎座", "射手座"
	}; 
	String GONG_LI = "公历：";
	String NONG_LI = "农历：";
	String TRADITIONAL_FEAST[] = 
	{
		"0101", "新年",
		"0103", "天庆节",
		"0105", "五路财神日",
		"0108", "江东神诞",
		"0109", "昊天皇帝诞",
		"0111", "太均娘娘诞",
		"0113", "散花灯 哥升节",
		"0115", "元宵节",
		"0116", "馄饨节 门神诞",
		"0119", "丘处机诞",
		"0120", "女娲补天日 黄道婆祭",
		"0125", "填仓节",
		"0127", "天地水三官诞",
		"0202", "龙头节 太昊伏羲氏祭",
		"0203", "文昌诞",
		"0208", "芳春节 插花节",
		"0210", "彩蛋节",
		"0212", "花朝节",
		"0215", "老子诞",
		"0219", "观音诞",
		"0228", "寒潮节 岱诞",
		"0303", "上巳节 踏青节",
		"0305", "大禹诞",
		"0310", "撒种节",
		"0315", "孙膑诞 龙华会",
		"0316", "蒙恬诞",
		"0318", "中岳节",
		"0320", "鲁班诞",
		"0322", "子孙娘娘诞",
		"0323", "天后玛祖诞",
		"0328", "仓颉先师诞",
		"0401", "清和节",
		"0402", "公输般日",
		"0408", "洗佛放生节 牛王诞 跳月节",
		"0410", "葛洪诞",
		"0411", "孔子祭",
		"0414", "吕洞宾诞 菖蒲日",
		"0415", "钟离权诞 外萨卡佛陀日",
		"0417", "金花女诞",
		"0418", "锡伯迁移节",
		"0419", "浣花日",
		"0426", "炎帝神农氏诞",
		"0428", "扁鹊诞",
		"0501", "女儿节",
		"0504", "采花节",
		"0505", "端午节",
		"0511", "范蠡祭",
		"0513", "关羽诞",
		"0522", "曹娥日",
		"0529", "祖娘节",
		"0606", "天贶节 盘古逝",
		"0612", "彭祖笺铿诞",
		"0615", "捕鱼祭",
		"0616", "爬坡节",
		"0619", "太阳日 观音日",
		"0623", "火神诞",
		"0624", "观莲节",
		"0707", "乞巧节",
		"0712", "地狱开门日",
		"0713", "轩辕诞",
		"0715", "中元节",
		"0723", "诸葛亮诞",
		"0727", "黄老诞",
		"0801", "天医节",
		"0803", "华佗诞",
		"0815", "中秋节",
		"0818", "观潮节",
		"0824", "稻节",
		"0909", "重阳节",
		"0913", "钉鞋日",
		"0916", "伯余诞",
		"0919", "观音逝",
		"0930", "采参节",
		"1001", "送寒衣节 祭祖节",
		"1015", "下元节 文成公主诞",
		"1016", "盘古节",
		"1208", "腊八节",
		"1212", "百福日 蚕花娘娘诞",
		"1223", "洗灶日",
		"1224", "小年",
		"1225", "上帝下界之辰"
	};
	
	String INTER_FEAST[] = 
	{
		"0101", "元旦",
		"0202", "世界湿地日",
		"0207", "国际声援南非日",
		"0210", "国际气象节",
		"0214", "情人节",
		"0301", "国际海豹日",
		"0303", "全国爱耳日",
		"0305", "学雷锋活动日",
		"0308", "国际妇女节",
		"0312", "植树节",
		"0314", "国际警察日",
		"0315", "消费者权益日",
		"0317", "中国国医节 国际航海日",
		"0321", "世界森林日 消除种族歧视国际日 世界儿歌日",
		"0322", "世界水日",
		"0323", "世界气象日",
		"0324", "世界防治结核病日",
		"0325", "全国中小学生安全教育日",
		"0330", "巴勒斯坦国土日",
		"0401", "愚人节",
		"0407", "世界卫生日",
		"0421", "_作者生日_",
		"0422", "世界地球日",
		"0423", "世界图书和版权日",
		"0424", "亚非新闻工作者日",
		"0501", "国际劳动节",
		"0504", "五四青年节",
		"0505", "碘缺乏病防治日",
		"0508", "世界红十字日",
		"0512", "国际护士节",
		"0515", "国际家庭日",
		"0517", "世界电信日",
		"0518", "国际博物馆日",
		"0520", "全国学生营养日",
		"0523", "国际牛奶日",
		"0531", "世界无烟日",
		"0601", "国际儿童节",
		"0605", "世界环境日",
		"0606", "全国爱眼日",
		"0617", "防治荒漠化和干旱日",
		"0623", "国际奥林匹克日",
		"0625", "全国土地日",
		"0626", "国际反毒品日",
		"0701", "中国共产党建党日 香港回归纪念日 世界建筑日",
		"0702", "国际体育记者日",
		"0707", "中国人民抗日战争纪念日",
		"0711", "世界人口日",
		"0730", "非洲妇女日",
		"0801", "八一建军节",
		"0908", "国际扫盲日",
		"0910", "中国教师节",
		"0914", "世界清洁地球日",
		"0916", "国际和平日 国际臭氧层保护日",
		"0918", "九·一八事变纪念日",
		"0920", "国际爱牙日",
		"0927", "世界旅游日",
		"1001", "国庆节 国际音乐日 国际老人节",
		"1002", "国际和平与民主自由斗争日",
		"1004", "世界动物日",
		"1005", "世界住房日",
		"1008", "全国高血压日 世界视觉日",
		"1009", "世界邮政日",
		"1010", "辛亥革命纪念日 世界精神卫生日",
		"1013", "世界保健日 国际教师节",
		"1014", "世界标准日",
		"1015", "国际盲人节(白手杖节)",
		"1016", "世界粮食日",
		"1017", "世界消除贫困日",
		"1022", "世界传统医药日",
		"1024", "联合国日 世界发展信息日",
		"1031", "世界勤俭日 万圣节前夜",
		"1107", "十月社会主义革命纪念日",
		"1108", "中国记者日",
		"1109", "全国消防安全宣传教育日",
		"1110", "世界青年节",
		"1114", "世界糖尿病日",
		"1117", "国际大学生节 世界学生节",
		"1121", "世界问候日 世界电视日",
		"1129", "国际声援巴勒斯坦人民国际日",
		"1201", "世界爱滋病日",
		"1203", "世界残疾人日",
		"1205", "国际经济和社会发展志愿人员日",
		"1208", "国际儿童电视日",
		"1209", "纪念一二·九运动 世界足球日",
		"1210", "世界人权日",
		"1212", "西安事变纪念日",
		"1213", "南京大屠杀(1937年)纪念日！紧记血泪史！",
		"1221", "国际篮球日",
		"1224", "平安夜",
		"1220", "澳门回归纪念日",
		"1225", "圣诞节",
		"1229", "国际生物多样性日"
	};
	String YEAR = "年";
	String MONTH = "月";
	String DATE = "日";
	char BRACKET1 = '[';
	char BRACKET2 = ']';
	//农历的日期常量定义结束
}