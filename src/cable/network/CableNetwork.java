/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cable.network;
import java.util.*;

public class CableNetwork
{
    
    public static void main(String[] args) 
    {
      int number_of_vertices; int number_of_edges;
      Scanner obj=new Scanner(System.in);
      System.out.println("Enter number of locations in the network : ");
      number_of_vertices=obj.nextInt();
      System.out.println("Enter number of edges in the network : ");
      number_of_edges=obj.nextInt();
      int[] vertices=new int[number_of_vertices];
      System.out.println("Enter the locations row-wise : ");
      for(int i=0;i<number_of_vertices;i++)
      {
          vertices[i]=obj.nextInt();
      }
      
      int[][] my_array= new int[number_of_vertices][number_of_vertices];
      int[][] arr= new int[3][number_of_edges];
      int k=0;
      for(int i=0;i<number_of_vertices;i++)
      {
          for(int j=0; j<number_of_vertices;j++)
          {
              if(i<j)
              {
                  System.err.println("Enter the distance on the edge:  "+vertices[i]+"----"+vertices[j]+" : ");
                  int entry= obj.nextInt();
                  my_array[i][j]=entry;
                  my_array[j][i]=entry;
                  if (my_array[i][j]!=0)
                  {
                      arr[0][k]=my_array[i][j];
                      arr[1][k]=(i*10)+j;
                      k+=1;
                  }
              }
          }
      }
      
      //sorting 2D my array using bubble sort
      for(int i=0;i<number_of_edges;i++)
      {
          for(int j=1;j<number_of_edges-i;j++)
          {
              if(arr[0][j-1]>arr[0][j])
              {
                  //snapping the first row
                  int temp=arr[0][j-1];
                  arr[0][j-1]=arr[0][j];
                  arr[0][j]=temp;
                  
                  //snapping the second row
                  int temp2=arr[1][j-1];
                  arr[1][j-1]=arr[1][j];
                  arr[1][j]=temp2;
              }
          }
      }
    
      
        System.out.println("\n\n MST        Cost"); 
        int retrieving_the_vertices=arr[1][0];
        int first_digit=retrieving_the_vertices/10;
        int second_digit=retrieving_the_vertices%10;
        System.out.println(first_digit+ "---->" +second_digit+ "      "+arr[0][0]);
        int total_sum=arr[0][0];
        int[][] to_avoid_loops=new int [number_of_edges][3];
        to_avoid_loops[0][0]=first_digit;
        to_avoid_loops[0][1]=second_digit;
        for(int i=1;i<number_of_edges;i++)
        {
            int first,second;
            int div=arr[1][i];
            if (div/10<1)
            {
                first = 0;
                second=arr[1][i];
                to_avoid_loops[i][0]=first;
                to_avoid_loops[i][1]=second;
            }
            else if(div/10>=1)
            {
                first=div/10;
                second=div%10;
                to_avoid_loops[i][0]=first;
                to_avoid_loops[i][1]=second;
            }
                
        }
      
        for(int for_loop_pointer=1;for_loop_pointer<number_of_edges;for_loop_pointer++)
        {
            int while_loop_pointer=for_loop_pointer;
            int index_of_current_pair_of_vertices=for_loop_pointer;
            int no_loop=0;
            int current_vertex=to_avoid_loops[while_loop_pointer][1];
            int looped= to_avoid_loops[while_loop_pointer][0];
            while(while_loop_pointer>0)
            {
                int s=0;
                if((current_vertex!=to_avoid_loops[while_loop_pointer-1][0] && current_vertex!=to_avoid_loops[while_loop_pointer-1][1]) || to_avoid_loops[while_loop_pointer-1][2]==-1)
                {
                    s+=1;
                }
                if(current_vertex==to_avoid_loops[while_loop_pointer-1][0] && to_avoid_loops[while_loop_pointer-1][2]==0)
                {
                    current_vertex=to_avoid_loops[while_loop_pointer-1][1];
                    if(current_vertex==looped)
                    {
                        no_loop=1;
                        break;
                    }
                    to_avoid_loops[while_loop_pointer-1][2]=-1;
                    while_loop_pointer=index_of_current_pair_of_vertices;
                }
                
                else if(current_vertex==to_avoid_loops[while_loop_pointer-1][1] && to_avoid_loops[while_loop_pointer-1][2]==0)
                {
                    current_vertex=to_avoid_loops[while_loop_pointer-1][0];
                    if(current_vertex==looped)
                    {
                        no_loop=1;
                        break;
                    }
                    to_avoid_loops[while_loop_pointer-1][2]=-1;
                    while_loop_pointer=index_of_current_pair_of_vertices;
                }
                if(s==1)
                {
                    while_loop_pointer-=1;
                }
            }
            for(int i=0;i<number_of_edges;i++)
            {
                to_avoid_loops[i][2]=0;
            }
            if(no_loop==0)
            {
                System.out.println(to_avoid_loops[index_of_current_pair_of_vertices][0]+"---->"+to_avoid_loops[index_of_current_pair_of_vertices][1]+"      "+arr[0][index_of_current_pair_of_vertices]);
                total_sum+=arr[0][index_of_current_pair_of_vertices];
            }
        }
   
        System.out.println("\n \n Cost of the minimum spanning tree is : "+total_sum);
        
        
    }
    
}
