import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.tika.Tika;

import java.io.File;

public class ContentExtraction {

    public static class LuceneIndexer {
        private final Tika tika;
        private final IndexWriter writer;

        public LuceneIndexer(Tika tika, IndexWriter writer) {
            this.tika = tika;
            this.writer = writer;
        }

        public void indexDocument(File file) {
            Document document = new Document();
        }
    }

    public static void main(String[] args) {

    }
}
