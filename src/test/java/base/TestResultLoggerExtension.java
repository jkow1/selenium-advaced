package base;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class TestResultLoggerExtension implements TestWatcher, AfterAllCallback {


    private List<TestResultStatus> testResultsStatus = new ArrayList<>();

    private enum TestResultStatus {
        SUCCESSFUL,
        FAILED
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        log.info("Test \"{}\" passed", context.getDisplayName());
        testResultsStatus.add(TestResultStatus.SUCCESSFUL);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        log.error("Test \"{}\" failed", context.getDisplayName(), cause);
        testResultsStatus.add(TestResultStatus.FAILED);
    }

    @Override
    public void afterAll(ExtensionContext context) {
        Map<TestResultStatus, Long> summary = testResultsStatus.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        log.info("Test result summary for {} {}", context.getDisplayName(), summary.toString());
    }
}
