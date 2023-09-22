public class drawRightTriangle {

    public static void createTriangle(char symbol, int height) {
        if (height <= 0)
            return;
        else {
            createTriangle(symbol, height-1);
            for (int i = 0; i < height; i++){
                System.out.print(symbol);
        }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        
        createTriangle('*', 10);
    }
}
