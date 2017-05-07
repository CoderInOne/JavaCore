package xunshan.templates;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihuangdong on 2017/5/7.
 *
 * 根据模板sql.ftl生成sql语句
 */
public class SqliteSqlTest {

    private final static String prefixPath = "../../templates";

    public static void main(String[] args) throws IOException, TemplateException {
        Template template = getTemplate(
                prefixPath,
                SqliteSqlTest.class,
                "sql.ftl");

        Map<String, Object> input = new HashMap<>();
        input.put("database_name", "note-db");
        input.put("table_name", "t_note");

        Writer consoleWriter = new OutputStreamWriter(System.out);
        template.process(input, consoleWriter);
    }

    private static Template getTemplate(String path, Class<?> loadclass, String templateName) throws IOException {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(loadclass, path);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setIncompatibleImprovements(new Version(2, 2, 20));
        return cfg.getTemplate(templateName);
    }
}
