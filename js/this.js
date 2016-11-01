/**
 * this的用法
 */

 // 1、纯粹的函数调用，此时的this代表全局对象window
 function test() {
     this.x = 1
 }
 test();
 x == 1;// true

 // 2、作为对象的方法调用，此时this代表调用者
 function test() {
     console.log(this.x);
 }
 var o = {};
 o.x = 2;
 o.f = test();
 o.f();// 2

 // 3、作为构造方法调用，此时this代表构造出的对象
 function test() {
     this.x = 3;
 }
 var o = new test();
 console.log(o.x);// 3
 // 4、apply调用,apply()是函数对象的一个方法。
 //    它的作用是改变函数的调用对象，它的第一个参数就表示改变后的调用这个函数的对象。
 //    因此，this指的就是这第一个参数。 
 function test() {
     console.log(this.x);
 }
 var o = {};
 o.x = 4;
 test.applay(o);// 4
