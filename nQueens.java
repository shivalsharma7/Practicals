public class nQueens{

    public static boolean isSafe(char board[][],int row,int col){
        //vetical up
        for(int i = row - 1; i >= 0; i--){
            if(board[i][col] == 'Q'){
                return false;
            }
        }

        //diag left up
        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0 ; i--, j--){
             if(board[i][j] == 'Q'){
                return false;
            }
        }

        //diag right up
        for(int i=row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++){
             if(board[i][j] == 'Q'){
                return false;
            }
        }
        return true;
    }
    public static void NQueens(char board[][],int row){
        //base
        if(row == board.length){
            printBoard(board);
            count++;
            return ;
        }
        //column loop
            for(int j=0;j<board.length;j++){
                if(isSafe(board,row,j)){
                board[row][j] = 'Q';
                NQueens(board,row+1);
                board[row][j] = 'x';
                }
                
                // if(NQueens(board,row+1)){
                    // return true;
                    // function call
                    // }
                //backtracking step  
                //this Nqueen recursive call function only get called if 
                // this board[row][j] = 'Q'; executes then then ahain traverse 
                // the next row
            }
           
                }
                   

    

    
    public static void printBoard(char board[][]){
        System.out.println("-----------CHESS BOARD------------");
        for(int i =0;i<board.length;i++){
            for(int j=0;j<board.length;j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    static int count = 0;
    public static void main(String[] args) {
        int n = 8;
        char board[][] = new char[n][n];
        //initialise
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                board[i][j] = 'x';
            }
        }
        NQueens(board, 0);
        System.out.println(count);
    }
}

