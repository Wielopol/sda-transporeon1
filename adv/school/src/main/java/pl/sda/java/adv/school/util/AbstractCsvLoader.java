package pl.sda.java.adv.school.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractCsvLoader <T> {
    public List<T> loadData(InputStream inputStream) throws IOException {
        final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        List<T> result = new LinkedList<>();
        while (bufferedReader.ready()) {
            final String value = bufferedReader.readLine();
//            System.out.println("Parsing line: " + value);

            parseLine(value).ifPresent(result::add);
        }
        return Collections.unmodifiableList(result);
    }

    protected abstract Optional<T> parseLine(String value);
}
