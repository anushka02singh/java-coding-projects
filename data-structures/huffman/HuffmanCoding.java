package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;

//import apple.laf.JRSUIUtils.Tree;

/**
 * This class contains methods which, when used together, perform the
 * entire Huffman Coding encoding and decoding process
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class HuffmanCoding {
    private String fileName;
    private ArrayList<CharFreq> sortedCharFreqList;
    private TreeNode huffmanRoot;
    private String[] encodings;

    /**
     * Constructor used by the driver, sets filename
     * DO NOT EDIT
     * @param f The file we want to encode
     */
    public HuffmanCoding(String f) { 
        fileName = f; 
    }

    /**
     * Reads from filename character by character, and sets sortedCharFreqList
     * to a new ArrayList of CharFreq objects with frequency > 0, sorted by frequency
     */
    public void makeSortedList() 
    {
        StdIn.setFile(fileName);
        int[] charArray = new int [128];
        int count = 0;
        sortedCharFreqList = new ArrayList<>();
        while(StdIn.hasNextChar())
        {
            //System.out.println("this is happening");
            char temp = StdIn.readChar();
            charArray[(int)temp]++;
            count++;
        }

        for(int i = 0; i < charArray.length-1; i++)
        {
            if(charArray[i] != 0)
            {
                CharFreq freq = new CharFreq((char)i, ((double)charArray[i]/(double)count));
                sortedCharFreqList.add(freq);
            }
        }

        if(sortedCharFreqList != null && sortedCharFreqList.size() == 1)
        {
            int a = (int)sortedCharFreqList.get(0).getCharacter()+1;
            if(a > 127)
            {
                a = 0;
            }
            CharFreq freqA = new CharFreq((char)a, 0.0);
            sortedCharFreqList.add(freqA);
        }
        Collections.sort(sortedCharFreqList);

    }

    /**
     * Uses sortedCharFreqList to build a huffman coding tree, and stores its root
     * in huffmanRoot
     */
    public void makeTree() 
    {
        Queue<TreeNode> source = new Queue<TreeNode>();
        Queue<TreeNode> target = new Queue<TreeNode>();

        while(!sortedCharFreqList.isEmpty())
        {
            TreeNode temp = new TreeNode();
            temp.setData(sortedCharFreqList.get(0));
            sortedCharFreqList.remove(0);
            source.enqueue(temp);

        }

        while(!source.isEmpty() || target.size() > 1)
        {
            TreeNode tempA = null;
            TreeNode tempB = null;

            if(target.isEmpty())
            {
                tempA = source.dequeue();
                tempB = source.dequeue(); 
            }
            else if (source.isEmpty())
            {
                tempA = target.dequeue();
                tempB = target.dequeue();
            }
            else
            {
                if(source.peek().getData().getProbOcc() <=  target.peek().getData().getProbOcc())
                {
                    tempA = source.dequeue();
                }
                else 
                {
                    tempA = target.dequeue();
                }

                if(source.isEmpty())
                {
                    tempB = target.dequeue();
                }
                else if(target.isEmpty())
                {
                    tempB = source.dequeue();
                }
                else if(source.peek().getData().getProbOcc() <=  target.peek().getData().getProbOcc())
                {
                    tempB = source.dequeue();
                }
                else
                {
                    tempB = target.dequeue();
                }

            }

            CharFreq info = new CharFreq(null, (tempA.getData().getProbOcc() + tempB.getData().getProbOcc()));
            TreeNode sum = new TreeNode(info, tempA, tempB);
            target.enqueue(sum);
        }

        huffmanRoot = target.dequeue();

    }

    /**
     * Uses huffmanRoot to create a string array of size 128, where each
     * index in the array contains that ASCII character's bitstring encoding. Characters not
     * present in the huffman coding tree should have their spots in the array left null.
     * Set encodings to this array.
     */
    public void makeEncodings() 
    {
        encodings = new String[128];
        for(int i = 0; i < encodings.length; i++)
        {
            String code = makeEncodingHelper((char) i, "", huffmanRoot);
            if(code.equals(""))
            {
                code = null;
            }

            encodings[i] = code;
        }

            //System.out.println(encodings[77]);
    }

    private String makeEncodingHelper(char x, String path, TreeNode target)
    {
        
        if(target == null)
        {
            path = "";
            //System.out.println(path);
            return path;
        }

        if(target.getData().getCharacter() != null && x == target.getData().getCharacter())
        {
            //System.out.println(path);
            return path;
        }

        return makeEncodingHelper(x, path + 0, target.getLeft()) + makeEncodingHelper(x, path + 1, target.getRight());
        
    }

    /**
     * Using encodings and filename, this method makes use of the writeBitString method
     * to write the final encoding of 1's and 0's to the encoded file.
     * 
     * @param encodedFile The file name into which the text file is to be encoded
     */
    public void encode(String encodedFile) 
    {
        StdIn.setFile(fileName);
        String temp = "";

        while(StdIn.hasNextChar())
        {
            char charInfo = StdIn.readChar();
            temp += encodings[(int)charInfo];
        }
        //System.out.println(temp);
        writeBitString(encodedFile, temp);

    }
    
    /**
     * Writes a given string of 1's and 0's to the given file byte by byte
     * and NOT as characters of 1 and 0 which take up 8 bits each
     * DO NOT EDIT
     * 
     * @param filename The file to write to (doesn't need to exist yet)
     * @param bitString The string of 1's and 0's to write to the file in bits
     */
    public static void writeBitString(String filename, String bitString) {
        byte[] bytes = new byte[bitString.length() / 8 + 1];
        int bytesIndex = 0, byteIndex = 0, currentByte = 0;

        // Pad the string with initial zeroes and then a one in order to bring
        // its length to a multiple of 8. When reading, the 1 signifies the
        // end of padding.
        int padding = 8 - (bitString.length() % 8);
        String pad = "";
        for (int i = 0; i < padding-1; i++) pad = pad + "0";
        pad = pad + "1";
        bitString = pad + bitString;

        // For every bit, add it to the right spot in the corresponding byte,
        // and store bytes in the array when finished
        for (char c : bitString.toCharArray()) {
            if (c != '1' && c != '0') {
                System.out.println("Invalid characters in bitstring");
                return;
            }

            if (c == '1') currentByte += 1 << (7-byteIndex);
            byteIndex++;
            
            if (byteIndex == 8) {
                bytes[bytesIndex] = (byte) currentByte;
                bytesIndex++;
                currentByte = 0;
                byteIndex = 0;
            }
        }
        
        // Write the array of bytes to the provided file
        try {
            FileOutputStream out = new FileOutputStream(filename);
            out.write(bytes);
            out.close();
        }
        catch(Exception e) {
            System.err.println("Error when writing to file!");
        }
    }

    /**
     * Using a given encoded file name, this method makes use of the readBitString method 
     * to convert the file into a bit string, then decodes the bit string using the 
     * tree, and writes it to a decoded file. 
     * 
     * @param encodedFile The file which has already been encoded by encode()
     * @param decodedFile The name of the new file we want to decode into
     */
    public void decode(String encodedFile, String decodedFile) 
    {
        StdOut.setFile(decodedFile);
        String info = readBitString(encodedFile);
        TreeNode ptr = huffmanRoot;
        //System.out.println(info);
        for(int i = 0; i < info.length(); i++)
        {
            if(info.charAt(i) == '0')
            {
                ptr = ptr.getLeft();
            }
            else
            {
                ptr = ptr.getRight();
            }

            if(ptr.getData().getCharacter() != null)
            {
                //System.out.print(ptr.getData().getCharacter());
                StdOut.print(ptr.getData().getCharacter());
                ptr = huffmanRoot;
            }
            
        }
    }

    /**
     * Reads a given file byte by byte, and returns a string of 1's and 0's
     * representing the bits in the file
     * DO NOT EDIT
     * 
     * @param filename The encoded file to read from
     * @return String of 1's and 0's representing the bits in the file
     */
    public static String readBitString(String filename) {
        String bitString = "";
        
        try {
            FileInputStream in = new FileInputStream(filename);
            File file = new File(filename);

            byte bytes[] = new byte[(int) file.length()];
            in.read(bytes);
            in.close();
            
            // For each byte read, convert it to a binary string of length 8 and add it
            // to the bit string
            for (byte b : bytes) {
                bitString = bitString + 
                String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
            }

            // Detect the first 1 signifying the end of padding, then remove the first few
            // characters, including the 1
            for (int i = 0; i < 8; i++) {
                if (bitString.charAt(i) == '1') return bitString.substring(i+1);
            }
            
            return bitString.substring(8);
        }
        catch(Exception e) {
            System.out.println("Error while reading file!");
            return "";
        }
    }

    /*
     * Getters used by the driver. 
     * DO NOT EDIT or REMOVE
     */

    public String getFileName() { 
        return fileName; 
    }

    public ArrayList<CharFreq> getSortedCharFreqList() { 
        return sortedCharFreqList; 
    }

    public TreeNode getHuffmanRoot() { 
        return huffmanRoot; 
    }

    public String[] getEncodings() { 
        return encodings; 
    }
}
