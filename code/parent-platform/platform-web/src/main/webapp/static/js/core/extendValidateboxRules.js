/**
 * 扩展easyui validate
 */
// 时间格式规范
var regex_dateTime = /\d{4}\-\d{2}\-\d{2} \d{2}\:\d{2}:\d{2}/;// @author ren
$.extend($.fn.validatebox.defaults.rules, {

    // 字符最大长度（param传参）
    maxLength: {
        validator: function(value, param) {
            return value.length <= param[0];
        },
        message: '您输入的字数太长了,最多{0}个字'
    },

    // 验证姓名，可以是中文或英文
    name: {
        validator: function(value) {
            return /^[\u0391-\uFFE5]{1,20}$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
        },
        message: '姓名字数过多或应为中文或者英文'
    },

    // 验证身份证
    idcard: {
        validator: function(value) {
            return /^\d{15}(\d{2}[Xx0-9])?$/i.test(value);
        },
        message: '身份证应为15位或者18位'
    },

    // 验证IP地址
    ip: {
        validator: function(value) {
            return /\d+\.\d+\.\d+\.\d+/.test(value);
        },
        message: 'IP地址格式不正确'
    },

    // 年龄验证
    age: {
        validator: function(value) {
            return /^[0-9]{1,2}$/i.test(value);// 0-99
        },
        message: '您输入的年龄不合法'
    },

    // 验证电话号码
    phone: {
        validator: function(value) {
            return /^1\d{10}$/i.test(value) || /^0\d{2,3}-?\d{7,8}$/i.test(value);
        },
        message: '电话号码正确格式:15288888888或020(0310)-88888888'
    },

    // 验证数字,整数或小数
    number: {
        validator: function(value) {
            return /^\d{1,10}(\.\d{0,4})?$/i.test(value);
        },
        message: '请输入正确的金额'
    },

    // 验证数字,只能为整数
    integer: {
        validator: function(value) {
            return /^\d{1,12}$/i.test(value);
        },
        message: '请输入一个整数'
    },

    // 时间验证
    /* start */
    endToStart: {
        validator: function(value, param) {
            return value > $("#" + param[0] + " input[name='" + param[1] + "']").val();// 结束时间>开始时间
        },
        message: '结束时间应晚于起始时间'
    },

    startToEnd: {
        validator: function(value, param) {
            return value > $("#" + param[0]).datetimebox('getValue');// 结束时间>开始时间
        },
        message: '结束时间应晚于起始时间'
    },

    datetimeValidate: {
        validator: function(value, param) {
            return regex_dateTime.test(value);// 验证时间格式是否规范
        },
        message: '时间格式应为 2015-01-01 12:00:00'
    },
    /* end */

    // 非法字符
    illeChar: {
        validator: function(value) {
            return !(/[<+]/.test(value));
        },
        message: '不可输入非法字符'
    }
});