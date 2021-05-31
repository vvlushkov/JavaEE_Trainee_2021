public class Main { //главны класс в котором находится метод main()

    //метод main()
    public static void main(String[] args){
        //создание и инициализация массива с квадратами
        int[][] array = new int[][] {
                {1, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 1},
                {1, 1, 0, 0, 0, 0},
                {1, 1, 1, 0, 1, 1}
        };
        //создание булевого массива под проверку посещаемости ячеек основного
        boolean[][] checkArray = new boolean[array.length][array[0].length];

        //создание объекта экземпляра класса с решением
        IcebergSolution solution = new IcebergSolution();

        //вызов метода с решением через экземпляр класса
        solution.icebergCounter(array, checkArray, 0, 0);

        //вызов метода для вывода через экземпляр класса
        solution.infoOutput();

    }
}
