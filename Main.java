
// A modified version of Christian Ullenboom's ZZZZZnake Code from the book "Java ist auch eine Insel"

import java.awt.*;  
import java.util.*; 

public class Main {

	public static boolean[][] collusion = new boolean[40][10];  // Check if the point is free
	public static int playerCount = 0;
	
	public static void printTheDate() {
	
		System.out.printf( "%tD%n", new Date() );
		System.out.println();
	}
	
	public static void printCodes() {
		System.out.println("    Move with W, A, S, D" );
		System.out.println("    S: Snake" );
		System.out.println("    &: Player" );
		System.out.println("    $: Gold" );
		System.out.println("    #: Door" );
		System.out.println("    Cheat codes: x , gold, door, fast, slow." );
		System.out.println("    Type info for Information." );
		System.out.println();
	}
	
public static void initializeToken( Point p ) { 
		
		int randomX = (int)(Math.random() * 40); // 0 <= x < 40
		int randomY = (int)(Math.random() * 10); // 0 <= y < 10		
		
		if( !collusion[randomX][randomY] ){ 
			
			p.setLocation( randomX, randomY ); 
			collusion[randomX][randomY]= true;
			playerCount++;
			
		}else 
		{
			if( playerCount>50 )
		  { System.out.println("Full!"); }
			else 
		  { 
				 do { System.out.println("Çakışma Bulundu!");
					  randomX = (int)(Math.random() * 40); // 0 <= x < 40
					  randomY = (int)(Math.random() * 10); // 0 <= y < 10
					  p.setLocation( randomX, randomY ); 
					  collusion[randomX][randomY]= true;
				    } while(!collusion[randomX][randomY]);				
		  }
		}		
		
	}
	
	public static void main(String[] args) {
		
        printTheDate();
		
		printCodes();
		
		Point playerPosition = new Point( 10, 9 );
		Point goldPosition   = new Point( 6, 6 );
		Point doorPosition   = new Point( 0, 5 );
		
		Point[] snakePositions = new Point[5];
		int snakeIdx = 0;
		snakePositions[ snakeIdx ] = new Point ( 30, 2 );
		
		boolean rich = false;
		boolean help = false;
		boolean freeze = false;
		int count = 0;
		int speed = 1; 
		
		System.out.println("-------------");
		System.out.println( "Player:" + playerPosition );
		System.out.println( "Snake:" + snakePositions[0] );
		System.out.println( "Gold:" + goldPosition );
		System.out.println( "Door:" + doorPosition );
		System.out.println("-------------");
		System.out.println();
		
		while (true) {
								
			if ( rich && playerPosition.equals( doorPosition ) ) {				
				System.out.println("You Win!");
				System.out.println("Count: " + count );
				break; // End while				
			}
			if ( Arrays.asList( snakePositions ).contains( playerPosition ) ) {
				
				System.out.println("You Lose!");
				System.out.println("Count: " + count );
				break;				
			}
            if ( playerPosition.equals( goldPosition ) ) {
				
				rich = true;
				goldPosition.setLocation( -1, -1 );				
			}
            
            // Draw the game            
            System.out.println("ooxxxxxxxxxxxxxXXXXXXXXXXxxxxxxxxxxxxxoo");
            
            for( int y = 0; y < 10; y++ ) {
            	for( int x = 0; x < 40; x++ ) {
            		
            		Point p = new Point( x, y );
                    if ( playerPosition.equals( p ) ) 
                    { System.out.print( '&' ); }
                    else if ( Arrays.asList( snakePositions ).contains( p ) ) 
                    { System.out.print( 'S' ); }
                    else if ( goldPosition.equals( p ) ) 
                    { System.out.print( '$' ); }
                    else if ( doorPosition.equals( p ) ) 
                    { System.out.print( '#' ); }
                    else
                    { System.out.print( '.' ); }
                    
            	}
            	System.out.println();
            }
            
            System.out.println("ooxxxxxxxxxxxxxXXXXXXXXXXxxxxxxxxxxxxxoo");
			System.out.println();
			
            // Inputs - Positioning
			
			System.out.println();
			System.out.print("Input: ");
			
			// TODO: Reduce number of cases by converting the input into lower case
            switch ( new java.util.Scanner( System.in ).next() ) {
              case "W" :
			  case "w" :
				playerPosition.y = Math.max( 0, playerPosition.y - speed );   break;
			  case "S" : 
			  case "s" : 
				playerPosition.y = Math.min( 9, playerPosition.y + speed );   break;
			  case "A" : 
			  case "a" : 
				playerPosition.x = Math.max( 0, playerPosition.x - speed );   break;
			  case "D" :
			  case "d" :
			  	playerPosition.x = Math.min( 39, playerPosition.x + speed );  break;
			 	case "GOLD" :
				case "gold" : playerPosition.setLocation( goldPosition.x, goldPosition.y ); break;
				case "DOOR" :
				case "door" : playerPosition.setLocation( doorPosition.x, doorPosition.y ); break;
				case "FAST" :
				case "fast" :  
					freeze = true;
					speed++;
					System.out.println( "Speed: " + speed );
					System.out.println();
					break;
				case "SLOW" :
				case "slow" : 
					if( speed > 1 ) { speed--; }   freeze = true;
					System.out.println( "Speed: " + speed );
					System.out.println();
					break;
				case "x" :
				case "X" :
					freeze = true;
					for( int i = 0; i < snakePositions.length; i++ )
					{
						snakePositions[ i ] = new Point ( 30, 2 );
					}
					break;
				case "info":
				case "INFO":
					freeze = true;
					System.out.println();
					System.out.println( "  **INFO**  " );
					System.out.println("Move with W, A, S, D" );
					System.out.println("    S: Snake" );
					System.out.println("    &: Player" );
					System.out.println("    $: Gold" );
					System.out.println("    #: Door" );
					System.out.println("Cheat codes: x , gold, door, fast, slow." );
					System.out.println( "  Positions: " );
					System.out.println( "Player: " + playerPosition );
					System.out.println( "Snake : " + snakePositions[snakeIdx] );
					System.out.println( "Gold  : " + goldPosition );
					System.out.println( "Door : " + doorPosition );
					System.out.println( "   Speed: " + speed );
					System.out.println( "   Count: " + count ); 
					System.out.println();
					break;
                
            }
            
            // Snake Positioning
            
            Point snakeHead = new Point( snakePositions[snakeIdx].x, snakePositions[snakeIdx].y );
            
            if( !help && !freeze) {
            
            if ( playerPosition.x < snakeHead.x ) {
            	snakeHead.x--;
            }
            else if ( playerPosition.x > snakeHead.x ) {
            	snakeHead.x++;
            }
            if ( playerPosition.y < snakeHead.y ) {
            	snakeHead.y--;
            }
            else if ( playerPosition.y > snakeHead.y ) {
            	snakeHead.y++;
            }
            
            snakeIdx = (snakeIdx + 1) % snakePositions.length ;
            snakePositions[snakeIdx] = snakeHead;
            
            count++;
            }
            
            help = false;
			freeze = false;
            
		} // End While		
		
	}

}
