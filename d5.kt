import java.io.File
import java.io.InputStream

class OP(c : Int, f : Int, t : Int)
{
	val count = c
	val from  = f
	val to    = t
}

fun flipStack(data : MutableList<String>): List<String>
{
	var newString = ""
	for(j in 0..data[0].count()-1)
	{
		for(i in 0..data.count()-1)
		{
			if(data[i][j].isLetter())
			{
				newString += data[i][j]
			}
			if(data[i][j].isDigit())
			{
				newString += "\n"
			}
		}
	}
	val data2 = newString.split("\n")
	var data3 = mutableListOf<String>()

	for(i in 0..data2.count()-1)
	{
		data3.add(data2[i].reversed())
	}

	return data3
} 

fun parseInstructions(data : MutableList<String>) : MutableList<OP>
{
	val program = mutableListOf<OP>()
	for(i in 1..data.count() - 1)
	{
		val strList = data[i].split(" ")
		program.add(OP(strList[1].toInt(),
					   strList[3].toInt(),
					   strList[5].toInt()))
	}
	return program
}

fun push(arg : String, data : Char) : String
{
	return arg + data;
}

fun pop(arg : String) : Char
{
	if(arg.length  > 1) 
	{
		return arg[arg.length-1]
	}
	else
	{
		return arg[0];
	}
}

fun transfer(arg1 : String, arg2 : String, count : Int) : String
{
	val len = arg1.length
	return arg2 + (arg1.subSequence((len - count), len))
}

fun newExecute(stacks : MutableList<String>, program : MutableList<OP>)
{
	program.forEach{
		var from = stacks[it.from - 1]
		var to = stacks[it.to - 1]
		val count = it.count

		to = transfer(from, to, count)
		from = from.dropLast(count)
		stacks[it.from -1] = from
		stacks[it.to -1] = to
	}

	println("\n STACKS:")
	stacks.forEach{
		println(it)
	}
}


fun execute(stacks : MutableList<String>, program : MutableList<OP>)
{
	program.forEach{
		var from = stacks[it.from - 1]
		var to = stacks[it.to - 1]
		val count = it.count

		for(i in 0..count-1)
		{
			println("FROM " + it.from + " : " + from + "\tTO" + it.to +" : " + to) 
			from = stacks[it.from - 1]
			to = stacks[it.to - 1]
			to = push(to, pop(from))
			from = from.dropLast(1)
			stacks[it.from -1] = from
			stacks[it.to -1] = to
		}

	}
	println("\n STACKS:")
	stacks.forEach{
		println(it)
	}
}

fun main(args: Array<String>) 
{
	val inputStream: InputStream = File("input").inputStream()
	val lineList = mutableListOf<String>()
	val instructions = mutableListOf<String>()
	val stacks = mutableListOf<String>()
	var dataCheck = false;
	
	inputStream.bufferedReader().forEachLine { 
		lineList.add(it) 
	} 
	
	lineList.forEach{
		if(it == "")
		{
			dataCheck = true;
		}
		if(dataCheck)
		{
			instructions.add(it)
		}
		else
		{
			stacks.add(it)
		}
	}
	var crates = flipStack(stacks)
	val data = mutableListOf<String>()
	crates.forEach{
		data.add(it)
	}
	val program : MutableList<OP> = parseInstructions(instructions)

	//execute(data,program)
	newExecute(data,program)
	
}

