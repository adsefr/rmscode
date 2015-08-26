package jp.co.rnai.talend.xml.generate;

import java.io.File;
import java.io.IOException;

import com.rms.common.xml.XMLFactory;
import com.rms.common.xml.eception.XMLParseException;
import com.rms.common.xml.impl.W3CXMLGenerator;
import com.rms.common.xml.node.DocumentNode;
import com.rms.common.xml.node.ElementNode;
import com.rms.common.xml.node.impl.ElementBuilder;
import com.rms.common.xml.node.impl.NodeBuilderFactory;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/24
 */
public class DelimitedFileConfigurator {

	private String[] labels = {};

	public static void main(String[] args) {

		String file = "D:/work/test/xml/generate/test.xml";
		W3CXMLGenerator generator = XMLFactory.newW3CXMLGenerator();
		try {
			generator.generate(generate());
			generator.write(new File(file));
		} catch (XMLParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	private static DocumentNode generate() {

		ElementBuilder elementBuilder = NodeBuilderFactory.newElementBuilder();
		elementBuilder.tagName("schema");

		String[] labels = { "id", "rm_user_id", "read_user_id", "login_id", "password", "handle", "handle_public_flag",
				"role_authority_id", "active_flag", "system_flag", "activate_key", "lang_dirname", "timezone_offset",
				"password_regist_time", "last_login_time", "previous_login_time", "permalink", "display_flag",
				"affiliationid", "data_exchange_flag", "data_update_flag", "data_update_state", "whatsnew_period",
				"whatsnew_period_public_flag", "rss_flag", "user_name", "user_name_en", "user_name_public_flag",
				"user_name_kana", "user_name_kana_public_flag", "user_another_name", "user_another_name_en",
				"user_another_name_public_flag", "avatar", "avatar_public_flag", "affiliation", "affiliation_en",
				"affiliation_public_flag", "other_affiliation", "other_affiliation_en",
				"other_affiliation_public_flag", "section", "section_en", "section_public_flag", "job", "job_en",
				"job_public_flag", "degree", "degree_en", "degree_public_flag", "profile", "profile_en",
				"profile_public_flag", "tel", "tel_public_flag", "birth_date", "birth_date_public_flag", "death_date",
				"death_date_public_flag", "email", "email_public_flag", "email_reception_flag", "other_email",
				"other_email_public_flag", "other_email_reception_flag", "mobile_email", "mobile_email_public_flag",
				"mobile_email_reception_flag", "gender", "gender_public_flag", "url", "url_en", "url_public_flag",
				"kakenid", "kakenid_public_flag", "twitter_name", "twitter_public_flag", "googleuaid",
				"googleuaid_public_flag", "eradid", "eradid_public_flag", "room_id", "insert_time",
				"insert_rm_user_id", "insert_user_name", "update_time", "update_rm_user_id", "update_user_name" };

		for (String label : labels) {
			elementBuilder.elementNodes(generateElementNode(label));
		}
		ElementNode rootElementNode = elementBuilder.build();

		DocumentNode documentNode = NodeBuilderFactory.newDocumentBuilder().rootElementNode(rootElementNode).build();

		return documentNode;

	}

	private static ElementNode generateElementNode(String labael) {

		return NodeBuilderFactory
				.newElementBuilder()
				//
				.tagName("column")
				//
				.attributeNodes(NodeBuilderFactory.newAttributeBuilder().name("label").value(labael).build())
				//
				.attributeNodes(NodeBuilderFactory.newAttributeBuilder().name("comment").value("").build())
				//
				.attributeNodes(NodeBuilderFactory.newAttributeBuilder().name("default").value("").build())
				//
				.attributeNodes(NodeBuilderFactory.newAttributeBuilder().name("key").value("false").build())
				//
				.attributeNodes(NodeBuilderFactory.newAttributeBuilder().name("length").value("0").build())
				//
				.attributeNodes(NodeBuilderFactory.newAttributeBuilder().name("nullable").value("true").build())
				//
				.attributeNodes(NodeBuilderFactory.newAttributeBuilder().name("pattern").value("").build())
				//
				.attributeNodes(NodeBuilderFactory.newAttributeBuilder().name("precision").value("0").build())
				//
				.attributeNodes(NodeBuilderFactory.newAttributeBuilder().name("originalDbColumnName").value("").build())//
				.attributeNodes(NodeBuilderFactory.newAttributeBuilder().name("talendType").value("id_String").build())//
				.build();
	}
}
