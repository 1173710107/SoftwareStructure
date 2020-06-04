package P1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
public class MagicSquare {
	public static void main(String[] args){
				String file[] = new String[5];
				 file[0] = "src/P1/txt/1.txt";  
				 file[1] = "src/P1/txt/2.txt";
				 file[2] = "src/P1/txt/3.txt";
				 file[3] = "src/P1/txt/4.txt";
				 file[4] = "src/P1/txt/5.txt";
	            System.out.println("文件测试数据如下：");
	            int i=0;
	            while(i!=5)
	            {
	            	 boolean x = false;
	 	            x =	isLegalMagicSquare(file[i]);
	 	            if(x)
	 	            {
	 	            	System.out.println("当前输入矩阵为幻方！！");
	 	            }
	 	            else
	 	            {
	 	            	System.out.println("当前输入矩阵不是幻方！！");
	 	            }
	 	            i++;
	            }
	            System.out.println("请输入需要创建的幻方的阶数：");
	            Scanner s=new Scanner(System.in);
	            int str=s.nextInt();
	            generateMagicSquare(str);
	 	}
	public static boolean isNumeric(String str){
		for(int i=str.length();--i>=0;){
		int chr=str.charAt(i);
		if(chr<48 || chr>57)
		return false;
		}
		return true;
	}
	public static boolean isLegalMagicSquare(String fileName){
	    	
	        try {
	        	BufferedReader br = new BufferedReader(new FileReader(fileName));//读取文件
	        	ArrayList<Integer> list = new ArrayList<Integer>();
	            String []sp = null;
	            int num[] =new int[150];
	            String line = null;
	            int count=0;
	            while((line=br.readLine())!=null) {//按行读取
	                sp = line.split("\t");//按tab进行分割
	                num[count] = sp.length;
	                if(num[count]!=num[0])//检查每行的数字个数是否相同
	                {
	                	JOptionPane.showMessageDialog(null, "输入矩阵行数不相同或分隔符并非\\t！！");
	                	System.out.println("输入矩阵行数不相同或分隔符并非\\t！！");
	                	return false;
	                }
	                for(int i=0;i<sp.length;i++){
	             
	                	/*float x = Float.parseFloat(sp[i]);
	                	int y = (int)x;
	                	if(x!=y)
	                	{
	                		System.out.println("当前输入矩阵中有小数！！");
	                		return false;
	                	}
	                	else if(y<0)
	                	{
	                		System.out.println("当前输入矩阵中有负数");
	                		return false;
	                	}*/
	                	if(isNumeric(sp[i]))
	                	{
	                		int x = Integer.valueOf(sp[i]);
	                		 list.add(x);	  
	                	}
	                	else
	                	{
	                		System.out.println("当前输入矩阵中有并不都是正整数！！！");
	                		return false;
	                	}
	                }
	                count++;
	            }
	            if(count!=num[0])//检查行列是否相同
	            {
	            	JOptionPane.showMessageDialog(null, "输入矩阵并非方阵！！");
                	System.out.println("输入矩阵并非方阵！！");
                	return false;
	            }
	            int i,j,sum1=0,sum2=0;
	            int sum[] =new int[count*2];
	            for(i=0;i<count;i++)
	            {
	            	for(j=0;j<count;j++)
	            	{
	            		sum[i] = sum[i] + list.get(count*i+j);//行累加
	            		sum[i+count] = sum[i+count] + list.get(count*j+i);//列累加
	            	}
	            	sum1 = sum1 + list.get(count*i+i);//左对角线
	            	sum2 = sum2 + list.get(i*count+count-i-1);//右对角线
	            }
	            if(sum1!=sum[0]||sum2!=sum[0])
	            {
	            	return false;
	            }
	            for(i=1;i<2*count;i++)
	            {
	            	if(sum[0]!=sum[i])
	            	{
	            		return false;
	            	}
	            }
	            

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return true;
	    }
	public static boolean generateMagicSquare(int n) {
		if(n<0)
		{
			JOptionPane.showMessageDialog(null, "输入数据n为负数，不符合规则！！");
			System.out.println("输入数据n为负数，不符合规则！！");
			return false;
		}
		int magic[][] = new int[n][n];//创建一个n*n的二维数组
		int row = 0, col = n / 2, i, j, square = n * n;//row为行，col为列，square是对应n阶的最大值减一，用于控制i
		for (i = 1; i <= square; i++) {
		if(row==n)
		{
			JOptionPane.showMessageDialog(null, "输入幻方阶数为偶数！！");
			System.out.println("输入幻方阶数为偶数！！");
			return false;
		}
		magic[row][col] = i;//给当前的row和col对应的二维数组的地方赋值当前i
		if (i % n == 0)//如果i是n的倍数，向下走
		{
			row++;
			/*System.out.println(row+" "+n+" "+i);
			if(row==n)
			{
				JOptionPane.showMessageDialog(null, "输入幻方阶数为偶数！！");
				System.out.println("输入幻方阶数为偶数！！");
				return false;
			}*/
		}
		else {//如果不是的话，向二维数组的右上走
		if (row == 0)//如果向上走到头，row变为最下面
		row = n - 1;
		else//否则向上走
		row--;
		if (col == (n - 1))//如果向右走到头的话，列变为最左边
		col = 0;
		else//否则向右走
		col++;
		}
		}
		try
		{
			File file = new File("src/P1/txt/6.txt");  //存放数组数据的文件

		     FileWriter bw = new FileWriter(file);  //文件写入流
		     BufferedWriter out = new BufferedWriter(bw);
			for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
			{
				out.write(magic[i][j]+"\t");
				System.out.print(magic[i][j] + "\t");//两层循环遍历二维数组
			}
			System.out.println();
			 out.write("\r\n");
			}
			 out.close();
			 bw.close();
		}catch (IOException e) {
            e.printStackTrace();
        }
		 boolean x = false;
		 String file1  = "src/P1/txt/6.txt";
          x =	isLegalMagicSquare(file1);
          if(x)
          {
          	System.out.println("当前产生的矩阵为幻方！！");
          }
          else
          {
          	System.out.println("当前产生的矩阵不是幻方！！");
          }
		return true;
		}

	
}
