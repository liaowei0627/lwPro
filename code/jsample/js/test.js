/**
 * 测试代码
 */
const crypto = require("crypto");
const pwd = "admin123";
const seed = "123456";
console.info("编码：");
var hash = crypto.createHash("md5");
hash.update(pwd);
var pwdCiphertext = hash.digest("hex").toUpperCase();
console.info(pwdCiphertext);
hash = crypto.createHash("md5");
hash.update(pwdCiphertext + seed);
pwdCiphertext = hash.digest("hex").toUpperCase();
console.info(pwdCiphertext);
var b = new Buffer(pwdCiphertext);
pwdCiphertext = b.toString("base64");
b = new Buffer(pwdCiphertext);
pwdCiphertext = b.toString("hex").toUpperCase();
console.info(pwdCiphertext);


console.info("解码：");
b = new Buffer(pwdCiphertext, "hex");
pwdCiphertext = b.toString();
b = new Buffer(pwdCiphertext, "base64");
pwdCiphertext = b.toString();
console.info(pwdCiphertext);