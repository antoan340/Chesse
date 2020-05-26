
import java.util.Scanner;

public class moveStets {
public static void getPosition(String[][] map,String[][] mineMap,int mapColumns,int mapRows,int end) {
    int count=0;
    FileReader fileReader = new FileReader("src/configurations.txt");
    int probes = fileReader.readData("number_of_probes");
    int disposal = fileReader.readData("number_of_disposal");

    do {
        Scanner word = new Scanner(System.in);
        int columns, rows;
        do {
            System.out.println("В коя колона е вашата позиция");
            columns = word.nextInt();
            columns++;
            System.out.println("В кой ред е вашата позиция");
            rows = word.nextInt();

        } while (map[rows][columns].equals("X") || map[rows][columns].equals("*"));
        System.out.println(map[rows][columns] + "+" + columns + "+" + rows);
        if (map[rows][columns].equals("S")) {
            moveType(map, mineMap, columns, rows, word, mapColumns, mapRows,probes,disposal);
        } else {
            System.out.println("Не мамете");
            getPosition(map, mineMap, mapColumns, mapRows, end);
        }
        if(map[0][end].equals("S")){
            count++;
        }
    }while (count<1);
}

public static void moveType(String[][] map,String[][] mineMap,int columns,int rows,Scanner word,int mapColumns,int mapRows,int probes,int disposal){
    System.out.println("1.Анализ");
    System.out.println("2.Обезвреждане");
    System.out.println("3.Придвижване,по бойното поле");
    int chose=word.nextInt();
        switch (chose) {
            case 1: {
                chekForMine(map,mineMap,columns,rows,word,probes);
                break;
            }
            case 2: {
                disposalForMine(map,mineMap,word,disposal);
                break;
            }
            default: {
                moveFoward(map, mineMap, columns, rows, word);
                break;
            }
        }

}
public static void moveFoward(String[][] map,String[][] mineMap,int columns,int rows,Scanner word){
    int newColumns,newRows;
    do {
        System.out.println("В коя колона е вашата нова позиция");
        newColumns = word.nextInt();
        newColumns++;
        System.out.println("В кой ред е вашата нова позиция");
        newRows = word.nextInt();

    }while (map[newRows][newColumns].equals("S")||map[newRows][newColumns].equals("*")||map[newRows][newColumns].equals("Y")||map[newRows][newColumns].equals("N"));
    if (newColumns-columns==1||columns-newColumns==1||columns-newColumns==0&&newRows-rows==1||rows-newRows==1||newRows-rows==0){

    }else {
        System.out.println("Въвелисте измамен ход");
        moveFoward(map, mineMap, columns, rows, word);
    }
    if(mineMap[newRows][newColumns].equals("x")||mineMap[newRows][newColumns].equals("V")||mineMap[newRows][newColumns].equals("F")||mineMap[newRows][newColumns].equals("N")){
        if(mineMap[newRows][newColumns].equals("F")){
            System.out.println("Вие победихте");
            System.exit(-1);
        }
        map[newRows][newColumns]="*";
        map[rows][columns]="V";
        mineMap[newRows][newColumns]="*";
        mineMap[rows][columns]="V";
        mapOptions.print(map);
    }
    else {
        System.out.println("Бяхте Взривени");
        System.exit(-1);
    }
}
public static void disposalForMine(String[][] map,String[][] mineMap,Scanner word,int disposal){
    int newColumns,newRows;
    do {
        System.out.println("В коя колона е мината");
        newColumns = word.nextInt();
        newColumns++;
        System.out.println("В кой ред е мината");
        newRows = word.nextInt();

    }while (mineMap[newRows][newColumns].equals("O"));
    if(disposal>0) {
        mineMap[newRows][newColumns] = "N";
        map[newRows][newColumns] = "N";
        disposal--;
    }else System.out.println("Нямате право на повече обезвреждания");
}
public static void chekForMine(String[][] map,String[][] mineMap,int columns,int rows,Scanner word,int probes){
    int newColumns,newRows;
    do {
        System.out.println("В коя колона е мината");
        newColumns = word.nextInt();
        newColumns++;
        System.out.println("В кой ред е мината");
        newRows = word.nextInt();

    }while (mineMap[newRows][newColumns].equals("x")||mineMap[newRows][newColumns].equals("V")||mineMap[newRows][newColumns].equals("F")||mineMap[newRows][newColumns].equals("N"));
    if(columns-newColumns==1||newColumns-columns==1){
        if(columns-newColumns==1){
            for (int i=newColumns;i<=newColumns+1;i++){
                for(int j=newRows-1;j<=newRows+1;j++){
                    if(mineMap[newRows][newColumns].equals("O")){
                        mineMap[newRows][newColumns]="Y";
                        map[newRows][newColumns]="Y";
                    }else {
                        mineMap[newRows][newColumns]="N";
                        map[newRows][newColumns]="N";
                    }
                }
            }
        }else {
            for (int i=columns;i<=columns-1;i--){
                for(int j=newRows+1;j<=newRows-1;j--){
                    if(mineMap[newRows][newColumns].equals("O")){
                        mineMap[newRows][newColumns]="Y";
                        map[newRows][newColumns]="Y";
                    }else {
                        mineMap[newRows][newColumns]="N";
                        map[newRows][newColumns]="N";
                    }
                }
            }
        }
    }else {
        if(columns-newColumns==1){
            for (int j=newRows;j<=newRows+1;j++){
                for(int i=columns-1;i<=columns+1;i--){
                    if(mineMap[newRows][newColumns].equals("O")){
                        mineMap[newRows][newColumns]="Y";
                        map[newRows][newColumns]="Y";
                    }else {
                        mineMap[newRows][newColumns]="N";
                        map[newRows][newColumns]="N";
                    }
                }
            }
        }else {
            for (int j=newRows;j<=newRows+1;j++){
                for(int i=columns+1;i<=columns-1;i--){
                    if(mineMap[newRows][newColumns].equals("O")){
                        mineMap[newRows][newColumns]="Y";
                        map[newRows][newColumns]="Y";
                    }else {
                        mineMap[newRows][newColumns]="N";
                        map[newRows][newColumns]="N";
                    }
                }
            }
        }
    }
    probes--;
}

}

