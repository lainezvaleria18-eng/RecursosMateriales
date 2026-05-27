package esfe.utils;

public class CBOption {
    private String displayText;
    private Object value;

    public CBOption(String displayText, Object value) {
        this.displayText = displayText;
        this.value = value;
    }

    public String getDisplayText() {
        return displayText;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return displayText; // Esto es lo que se mostrará visualmente en tus JComboBox
    }

    @Override
    public boolean equals(Object obj) {
        // Verifica si el objeto con el que se está comparando es nulo.
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }


        final CBOption other = (CBOption) obj;


        if (this.value == null && other.value == null) {
            return true;
        }

        if (this.value == null || other.value == null) {
            return false;
        }

        return this.value.equals(other.value);
    }
}