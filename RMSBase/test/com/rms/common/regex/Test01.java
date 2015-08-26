package com.rms.common.regex;


public class Test01 {

	public static void main(String[] args) {

		System.out.println("555555555555\t555555-5".matches("[[.][\t]&&[^ｰﾀﾐ｡ｱﾁﾑ｢ｲﾂﾒ｣ｳﾃﾓ､ｴﾄﾔ･ｵﾅﾕｦｶﾆﾖｧｷﾇﾗｨｸﾈﾘｩｹﾉﾙｪｺﾊﾚｫｻﾋﾛｬｼﾌﾜｭｽﾍﾝｮｾﾎﾞｯｿﾏﾟ]]*"));
		System.out.println("55555555 555 5\t5^5\r\n5|　5カナ5.\r\n5-5".matches("[[\\S\t\r\n ]&&[^ｰﾀﾐ｡ｱﾁﾑ｢ｲﾂﾒ｣ｳﾃﾓ､ｴﾄﾔ･ｵﾅﾕｦｶﾆﾖｧｷﾇﾗｨｸﾈﾘｩｹﾉﾙｪｺﾊﾚｫｻﾋﾛｬｼﾌﾜｭｽﾍﾝｮｾﾎﾞｯｿﾏﾟ]]*"));
	}
}
