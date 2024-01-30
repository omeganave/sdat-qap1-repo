package org.example.qap1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class SuggestionEngineTest {
    private SuggestionEngine suggestionEngine = new SuggestionEngine();

    @Mock
    private SuggestionsDatabase suggestionsDatabase = new SuggestionsDatabase();
//    private boolean testInstanceSame = false;

    @Test
    public void testGenerateSuggestions() throws Exception {
        suggestionEngine.loadDictionaryData( Paths.get( ClassLoader.getSystemResource("words.txt").getPath()));

        Assertions.assertTrue(suggestionEngine.generateSuggestions("wrd").contains("wyrd"));
        Assertions.assertTrue(suggestionEngine.generateSuggestions("ccrate").contains("create"));

        Assertions.assertTrue(suggestionEngine.generateSuggestions("frm").contains("form"));
        Assertions.assertTrue(suggestionEngine.generateSuggestions("frm").contains("from"));
        Assertions.assertTrue(suggestionEngine.generateSuggestions("frm").contains("firm"));
    }

    @Test
    public void testCorrectWord() throws Exception {
        suggestionEngine.loadDictionaryData( Paths.get( ClassLoader.getSystemResource("words.txt").getPath()));

        Assertions.assertEquals("", suggestionEngine.generateSuggestions("word"));
        Assertions.assertEquals("", suggestionEngine.generateSuggestions("another"));
        Assertions.assertEquals("", suggestionEngine.generateSuggestions("correctly"));
        Assertions.assertFalse(suggestionEngine.generateSuggestions("suggestion").contains("suggestions"));
    }

    @Test
    public void testInvalidWordsFile() throws Exception {
        Assertions.assertThrows(IOException.class, () -> {
            suggestionEngine.loadDictionaryData(Paths.get("other-words.txt"));
        });

//        Assertions.assertTrue(suggestionEngine.getWordSuggestionDB().isEmpty());
    }
}
