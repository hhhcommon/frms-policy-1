package cn.com.bsfit.frms.policy;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

import cn.com.bsfit.frms.obj.AuditObject;

public class EngineTest {

	private static final String URL = "http://127.0.0.1:9180/audit";
	private static final Integer POOL_SIZE = 4;
	
	public static void main(String[] args) throws ParseException {
		PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager();
		connMgr.setMaxTotal(POOL_SIZE + 1);
		connMgr.setDefaultMaxPerRoute(POOL_SIZE);
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connMgr).build();
		RestTemplate template = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		FastJsonHttpMessageConverter fastjson = new FastJsonHttpMessageConverter();
		fastjson.setFeatures(SerializerFeature.WriteClassName);
		converters.add(fastjson);
		template.setMessageConverters(converters);
		String list = template.postForObject(URL, getAuditObject(), String.class);
		System.out.println(list);
	}

	public static List<AuditObject> getAuditObject() throws ParseException {
		List<AuditObject> auditObjectList = new ArrayList<AuditObject>();
		for (int i = 0; i < 10000; i++) {
			AuditObject auditObject = new AuditObject();

			auditObject.setBizCode("TRANSFER");
			auditObject.put("frms_data_type", "azjcph");
			auditObject.setTransTime(new Date());
			auditObject.put("frms_trans_vol", 160000L * ((i + 1) * 1000) );

			auditObject.put("frms_oper_status", "0");// 特殊标志:0-"正常";1-"被冲账";2-"冲账";3-"被抹账";4-"抹账";5-"挂账";
			auditObject.put("frms_order_chnl", "17");// 交易渠道：网上银行-"17";手机银行-"51";柜面交易-""00""；本行ATM-"08"；本行pos-"22"
			auditObject.put("frms_order_type", "0");// 借贷标志 0-消费 1-收入

			auditObject.put("frms_trade_mode", "0");//// 开销户标志0-"全部";1-"开户";2-"销户"
			auditObject.put("frms_trade_type", "1");// 0-现金1-转账
			auditObject.put("frms_user_id", "9527"); // 客户号
			auditObject.put("frms_bank_card_no", "62111111111");// 银行卡号

			auditObject.put("frms_user_type", "1");// 1-企业用户；2-个人用户
			auditObject.put("frms_company_name", "SBB"); // 如果是企业用户(只有当企业用户时才有)
			auditObject.put("frms_user_id_card", "77777777"); // 如果是个人用户(只有个人用户时才有)

			auditObject.put("frms_col_name", "张三");// 收款用户名(当该字段有值时才确定)
			auditObject.put("frms_col_card_no", "60000000000");// 收款人卡号

			auditObject.put("frms_oper_no", "9528");// 操作柜员号
			auditObject.put("frms_user_name", "Shit");
			auditObject.put("frms_pay_detail", "Fuck");
			auditObject.put("frms_trans_code", "Transfer");

			auditObject.put("frms_return_code", "000000");
			auditObject.put("frms_return_msg", "正常交易");
			auditObject.put("frms_ip_address", "127.8.0.1");

			auditObjectList.add(auditObject);
		}

		return auditObjectList;
	}
}
