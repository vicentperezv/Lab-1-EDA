
package lab1;

import java.util.*; 
import java.io.*; 


public class Lab1 {

    
    
   
    public static void main(String[] args) {
        BufferedReader reader;
        BufferedWriter writer1;
        BufferedWriter writer2; 
        BufferedWriter writer3; 
        String nombre = null;
        int cont = 0;
        cola cola1 = new cola();
        pila pila1 = new pila();
        String[] arr =  {"./mx-amazon-devices.csv", "./mx-automotive.csv","./mx-baby.csv"
        ,"./mx-books.csv","./mx-digital-text.csv","./mx-dvd.csv","./mx-electronics.csv"
        ,"./mx-grocery.csv","./mx-handmade.csv","./mx-hpc.csv","./mx-kitchen.csv"
        ,"./mx-music.csv","./mx-musical-instruments.csv","./mx-officeproduct.csv",
        "./mx-pet-supplies.csv", "./mx-shoes.csv","./mx-software.csv"
        ,"./mx-sports.csv","./mx-tools.csv", "./mx-toys.csv","./mx-videogames.csv"};
        
        
        //"./mx-industrial.csv",
        
        try{
        writer1 = new BufferedWriter(new FileWriter("masvendidos.txt",false));
        writer2 = new BufferedWriter(new FileWriter("creciente.txt",false));
        writer1.write("Productos más solicitados de cada categoría:  " + "\n"
                +"Categoria//Nombre del producto//Cantidad de ventas"+ "\n");
        writer2.write("Productos más solicitados en orden creciente:  " + "\n"
                +"Categoria//Nombre del producto//Cantidad de ventas"+ "\n");
        
        for(String aux : arr){
            int i = 0;
            if(cont == 0){
                nombre ="amazon_devices";
            }else if(cont == 1){
                nombre = "automotive";
            }else if(cont == 2){
                nombre = "baby";
            }else if(cont == 3){
                nombre = "books";
            }else if(cont == 4){
                nombre = "digital_text";
            }else if(cont == 5){
                nombre = "dvd";
            }else if(cont == 6){
                nombre = "electronics";
            }else if(cont == 7){
                nombre = "grocery";
            }else if(cont == 8){
                nombre = "handmade";
            }else if(cont == 9){
                nombre = "hpc";
            }else if(cont == 10){
                nombre = "kitchen";
            }else if(cont == 11){
                nombre = "music";
            }else if(cont == 12){
                nombre = "musical_instruments";
            }else if(cont == 13){
                nombre = "officeproduct";
            }else if(cont == 14){
                nombre = "pet_supplies";
            }else if(cont == 15){
                nombre = "shoes";
            }else if(cont == 16){
                nombre = "software";
            }else if(cont == 17){
                nombre = "sports";
            }else if(cont == 18){
                nombre = "tools";
            }else if(cont == 19){
                nombre = "toys";
            }else if(cont == 20){
                nombre = "videogames";
            }
            ArrayList<ArrayList<String>> dataset = new ArrayList<ArrayList<String>>();
            reader = new BufferedReader(new FileReader(aux));
            writer3 = new BufferedWriter(new FileWriter("mx-"+nombre+"_salida.csv", false));
           
            
            String line = reader.readLine();
            while (line != null){
		ArrayList<String> parsing1 = new ArrayList<String>();
		String[] row1;
		row1 = line.split("\\|",-1);
		for(String x : row1){
                    parsing1.add(x);
		}
		dataset.add(parsing1);
		line = reader.readLine();
            }
            for(ArrayList a : dataset)	{
		for( i = 0; i < a.size()-1; i++){
                    writer3.write(a.get(i).toString());
                    writer3.write("|");
		}
		writer3.write(a.get(i).toString());
		writer3.write("\n");
            }
            
            ArrayList<tripleta> t_count = new ArrayList<tripleta>();
            
        
            for(int progress_index = 1; progress_index < dataset.size(); progress_index++){
		String prod_name = dataset.get(progress_index).get(3);
		boolean found = false;
		for(tripleta search : t_count){
                    if(search.get_producto().equals(prod_name)){
                    found = true;
                    search.incConteo();
                    }
            }
            if(!found){
		tripleta new_tripleta = new tripleta(nombre, prod_name);
		t_count.add(new_tripleta);
		}
            }

                        
            int largest;

            for (int k=0; k < t_count.size () - 1; k++)
            {
            largest = 0;
            for (int j=largest + 1; j < t_count.size () - k; j++)
             {
             if ((t_count.get (largest)).compareTo (t_count.get (j)) < 0)
                {
                 largest = j;
                 }
             }
            tripleta tempTrip = t_count.get (t_count.size () - 1 - k);
            t_count.set (t_count.size () - 1 - k, t_count.get (largest));
            t_count.set (largest, tempTrip);
            }
    
            int aux2 = 0;
            for(tripleta t_d : t_count){
		cola1.enqueue(t_d);
                if(aux2 == 0){
                    writer1.write(t_d.get_categoria() + "//" + t_d.get_producto() + "//" + t_d.get_conteo() + "\n");
                    aux2 ++;
                }
                
            }

            
	
                       
            reader.close();
            writer3.close();
            cont ++;
        }
        while(!cola1.isEmpty()){
                
		pila1.push((tripleta)cola1.dequeue());
                
                
        }
            
        while(!pila1.isEmpty()){
            tripleta t_p;
            t_p = (tripleta)pila1.pop();
                    
            writer2.write(t_p.get_categoria() + "//" + t_p.get_producto() + "//" + t_p.get_conteo() + "\n");
                    
        }
                              
	writer1.close();
        writer2.close();
        
        }catch(IOException e){
		e.printStackTrace();
	}
        
            
    }
    
    
}
