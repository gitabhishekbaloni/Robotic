package classes;

public enum Direction {
        East("E"),
        West("W"),
        North("N"),
        South("S");

        public final String value;

        private Direction(String value) {
                this.value = value;
        }
}
