package tv.automation.utils;

import java.io.IOException;

public interface TestTemplate {

    void globalSetup() throws IOException;

    void globalTearDown();
}
