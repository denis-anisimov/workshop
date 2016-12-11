package test;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 * @author denis
 *
 */
public class AbstractDocument {

    /**
     * Gets a sequence of text from the document.
     *
     * @param offset
     *            the starting offset &gt;= 0
     * @param length
     *            the number of characters to retrieve &gt;= 0
     * @return the text
     * @exception BadLocationException
     *                the range given includes a position that is not a valid
     *                position within the document
     * @see Document#getText
     */
    public String getText(int offset, int length) throws BadLocationException {
        if (length < 0) {
            throw new BadLocationException("Length must be positive", length);
        }
        String str = data.getString(offset, length);
        return str;
    }

    /**
     * Deletes the region of text from <code>offset</code> to
     * <code>offset + length</code>, and replaces it with <code>text</code>. It
     * is up to the implementation as to how this is implemented, some
     * implementations may treat this as two distinct operations: a remove
     * followed by an insert, others may treat the replace as one atomic
     * operation.
     *
     * @param offset
     *            index of child element
     * @param length
     *            length of text to delete, may be 0 indicating don't delete
     *            anything
     * @param text
     *            text to insert, <code>null</code> indicates no text to insert
     * @param attrs
     *            AttributeSet indicating attributes of inserted text,
     *            <code>null</code> is legal, and typically treated as an empty
     *            attributeset, but exact interpretation is left to the subclass
     * @exception BadLocationException
     *                the given position is not a valid position within the
     *                document
     * @since 1.4
     */
    public void replace(int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (length == 0 && (text == null || text.length() == 0)) {
            return;
        }
        DocumentFilter filter = getDocumentFilter();

        writeLock();
        try {
            if (filter != null) {
                filter.replace(getFilterBypass(), offset, length, text, attrs);
            } else {
                if (length > 0) {
                    remove(offset, length);
                }
                if (text != null && text.length() > 0) {
                    insertString(offset, text, attrs);
                }
            }
        } finally {
            writeUnlock();
        }
    }

}
