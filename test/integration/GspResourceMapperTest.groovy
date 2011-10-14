import org.codehaus.groovy.grails.web.pages.GroovyPagesTemplateEngine

class GspResourceMapperTest extends GroovyTestCase {
    GspResourceMapper gspResourceMapper
    GroovyPagesTemplateEngine groovyPagesTemplateEngine

    public void setUp() {
        gspResourceMapper = new GspResourceMapper()
        gspResourceMapper.groovyPagesTemplateEngine = groovyPagesTemplateEngine
    }

    public void test_compile_simple_template() {
        assertCompilationOutput(new File('web-app/css/simple-template.css.gsp'),
                "h1{color: red}h2{color: pink}h3{color: blue}h4{color: green}")
    }

    public void test_compile_template_with_tags() {
        assertCompilationOutput(new File('web-app/css/template-with-tags.css.gsp'),
                "h1{border: solid 3px black}h2{border: solid 3px gray}h3{border: solid 3px black}h4{border: solid 3px gray}")
    }

    public void test_compile_static_reference() {
        assertCompilationOutput(new File('web-app/css/static-reference.js.gsp'), "var x=1;")
    }

    private void assertCompilationOutput(File file, String expectedOutput) {
        String output = gspResourceMapper.compileGsp(file)
        assertEquals("Expected:\n$expectedOutput\n\nActual:\n$output", expectedOutput.trim(), output.trim())
    }
}