/**
 * 测试代码
 */
var CryptoJS = require("crypto-js");
var pwd = "admin123";
var seed = "123456";

var message = CryptoJS.lib.WordArray.create([0x12345678]);
var key = CryptoJS.lib.WordArray.create([0x12345678]);

var expectedMessage = message.toString();
var expectedKey = key.toString();

CryptoJS.HmacMD5(message, key);

console.info(expectedMessage);
console.info(message.toString());
console.info(expectedKey);
console.info(key.toString());


//console.info("编码：");
//var hash = CryptoJS.HmacMD5("md5");
//hash.update(pwd);
//var pwdCiphertext = hash.digest("hex").toUpperCase();
//console.info(pwdCiphertext);
//hash = crypto.createHash("md5");
//hash.update(pwdCiphertext + seed);
//pwdCiphertext = hash.digest("hex").toUpperCase();
//console.info(pwdCiphertext);
//var b = new Buffer(pwdCiphertext);
//pwdCiphertext = b.toString("base64");
//b = new Buffer(pwdCiphertext);
//pwdCiphertext = b.toString("hex").toUpperCase();
//console.info(pwdCiphertext);


console.info("解码：");
b = new Buffer(pwdCiphertext, "hex");
pwdCiphertext = b.toString();
b = new Buffer(pwdCiphertext, "base64");
pwdCiphertext = b.toString();
console.info(pwdCiphertext);