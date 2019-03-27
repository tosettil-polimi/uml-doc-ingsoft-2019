public enum Color {
    RED("\u001B[101m"), GREEN("\u001B[102m"), YELLOW("\u001B[103m"), BLUE("\u001B[104m"), PURPLE("\u001B[105m"), GREY("\u001B[107m"), RESET("\u001B[0m");

    private String color;

    public String getColor() {
        return color;
    }

    private Color(String color) {
        this.color = color;
    }
}
