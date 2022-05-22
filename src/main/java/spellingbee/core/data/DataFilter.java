package spellingbee.core.data;

import java.util.List;

public interface DataFilter {
    FilteredData filter(List<String> words, String letters);

    FilteredData filter(List<String> words);

}
