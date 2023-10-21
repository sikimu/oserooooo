package oserooooo;

public enum Stone {
    BLACK(1),
    WHITE(2);

    private int value;

    Stone(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    /**
     * 相手の石を取得する
     * @return
     */
    public Stone getOpponent() {
        return this == BLACK ? WHITE : BLACK;
    }
}
