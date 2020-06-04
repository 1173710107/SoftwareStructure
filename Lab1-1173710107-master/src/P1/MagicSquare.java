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
	            System.out.println("�ļ������������£�");
	            int i=0;
	            while(i!=5)
	            {
	            	 boolean x = false;
	 	            x =	isLegalMagicSquare(file[i]);
	 	            if(x)
	 	            {
	 	            	System.out.println("��ǰ�������Ϊ�÷�����");
	 	            }
	 	            else
	 	            {
	 	            	System.out.println("��ǰ��������ǻ÷�����");
	 	            }
	 	            i++;
	            }
	            System.out.println("��������Ҫ�����Ļ÷��Ľ�����");
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
	        	BufferedReader br = new BufferedReader(new FileReader(fileName));//��ȡ�ļ�
	        	ArrayList<Integer> list = new ArrayList<Integer>();
	            String []sp = null;
	            int num[] =new int[150];
	            String line = null;
	            int count=0;
	            while((line=br.readLine())!=null) {//���ж�ȡ
	                sp = line.split("\t");//��tab���зָ�
	                num[count] = sp.length;
	                if(num[count]!=num[0])//���ÿ�е����ָ����Ƿ���ͬ
	                {
	                	JOptionPane.showMessageDialog(null, "���������������ͬ��ָ�������\\t����");
	                	System.out.println("���������������ͬ��ָ�������\\t����");
	                	return false;
	                }
	                for(int i=0;i<sp.length;i++){
	             
	                	/*float x = Float.parseFloat(sp[i]);
	                	int y = (int)x;
	                	if(x!=y)
	                	{
	                		System.out.println("��ǰ�����������С������");
	                		return false;
	                	}
	                	else if(y<0)
	                	{
	                		System.out.println("��ǰ����������и���");
	                		return false;
	                	}*/
	                	if(isNumeric(sp[i]))
	                	{
	                		int x = Integer.valueOf(sp[i]);
	                		 list.add(x);	  
	                	}
	                	else
	                	{
	                		System.out.println("��ǰ����������в�������������������");
	                		return false;
	                	}
	                }
	                count++;
	            }
	            if(count!=num[0])//��������Ƿ���ͬ
	            {
	            	JOptionPane.showMessageDialog(null, "������󲢷Ƿ��󣡣�");
                	System.out.println("������󲢷Ƿ��󣡣�");
                	return false;
	            }
	            int i,j,sum1=0,sum2=0;
	            int sum[] =new int[count*2];
	            for(i=0;i<count;i++)
	            {
	            	for(j=0;j<count;j++)
	            	{
	            		sum[i] = sum[i] + list.get(count*i+j);//���ۼ�
	            		sum[i+count] = sum[i+count] + list.get(count*j+i);//���ۼ�
	            	}
	            	sum1 = sum1 + list.get(count*i+i);//��Խ���
	            	sum2 = sum2 + list.get(i*count+count-i-1);//�ҶԽ���
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
			JOptionPane.showMessageDialog(null, "��������nΪ�����������Ϲ��򣡣�");
			System.out.println("��������nΪ�����������Ϲ��򣡣�");
			return false;
		}
		int magic[][] = new int[n][n];//����һ��n*n�Ķ�ά����
		int row = 0, col = n / 2, i, j, square = n * n;//rowΪ�У�colΪ�У�square�Ƕ�Ӧn�׵����ֵ��һ�����ڿ���i
		for (i = 1; i <= square; i++) {
		if(row==n)
		{
			JOptionPane.showMessageDialog(null, "����÷�����Ϊż������");
			System.out.println("����÷�����Ϊż������");
			return false;
		}
		magic[row][col] = i;//����ǰ��row��col��Ӧ�Ķ�ά����ĵط���ֵ��ǰi
		if (i % n == 0)//���i��n�ı�����������
		{
			row++;
			/*System.out.println(row+" "+n+" "+i);
			if(row==n)
			{
				JOptionPane.showMessageDialog(null, "����÷�����Ϊż������");
				System.out.println("����÷�����Ϊż������");
				return false;
			}*/
		}
		else {//������ǵĻ������ά�����������
		if (row == 0)//��������ߵ�ͷ��row��Ϊ������
		row = n - 1;
		else//����������
		row--;
		if (col == (n - 1))//��������ߵ�ͷ�Ļ����б�Ϊ�����
		col = 0;
		else//����������
		col++;
		}
		}
		try
		{
			File file = new File("src/P1/txt/6.txt");  //����������ݵ��ļ�

		     FileWriter bw = new FileWriter(file);  //�ļ�д����
		     BufferedWriter out = new BufferedWriter(bw);
			for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
			{
				out.write(magic[i][j]+"\t");
				System.out.print(magic[i][j] + "\t");//����ѭ��������ά����
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
          	System.out.println("��ǰ�����ľ���Ϊ�÷�����");
          }
          else
          {
          	System.out.println("��ǰ�����ľ����ǻ÷�����");
          }
		return true;
		}

	
}
