
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
public class mapOptions {
    public static void mapCreate(){
        Scanner word = new Scanner(System.in);
        int columnsCounter=0,rowsCounter=0,start,end,bombColumns,bombRows;
        FileReader fileReader = new FileReader("src/enemy_teritory.txt");
        int columns = fileReader.readData("width");
        int rows = fileReader.readData("height");
        int bombs =fileReader.readData("mines");
        String[][] map=new String[rows+1][columns+1];
        String[][] mineMap=new String[rows+1][columns+1];
        for (int i=0;i<rows+1;i++){
            for(int j=0;j<columns+1;j++){
                if(i<rows) {
                    map[i][j] = "x";
                    mineMap[i][j]="x";
                    if (j == 0) {
                        map[i][j] = Integer.toString(rowsCounter);
                        mineMap[i][j] = Integer.toString(rowsCounter);
                        rowsCounter++;
                    }
                }
                if(i==rows) {
                    if(j==0){
                        map[i][j] =" ";
                        mineMap[i][j]=" ";
                    }
                    else {
                        map[i][j] = Integer.toString(columnsCounter);
                        mineMap[i][j] = Integer.toString(columnsCounter);
                        columnsCounter++;
                    }
                }
            }
        }
        do {
            start = ThreadLocalRandom.current().nextInt(1, columns+1 );
        }while (start>1&&start<columns);
        map[0][start]="F";
        mineMap[0][start]="F";
        do {
            end = ThreadLocalRandom.current().nextInt(1, columns+1);
        }while (end>1&&end<columns);
        map[rows-1][end]="S";
        mineMap[rows-1][end]="S";
        do {
            bombColumns= ThreadLocalRandom.current().nextInt(1, columns+1 );
            bombRows= ThreadLocalRandom.current().nextInt(1, rows+1 );
            if(mineMap[bombRows][bombColumns]=="x"){
                mineMap[bombRows][bombColumns]="O";
                bombs--;
            }
        }while (bombs>0);
        int mapColumns=columns,mapRows=rows;
        print(map);
      moveStets.getPosition(map,mineMap,mapColumns,mapRows,end);

    }
    public static void print(String[][] map){
        for (String[] row : map) {
            for (String c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

}

