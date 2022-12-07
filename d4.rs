use std::
{
    fs::File,
    io::{self,BufRead},
    path::Path,
};

struct Camp
{
    p1s :u32,
    p1e :u32,
    p2s :u32,
    p2e :u32
}

//Don't ask how this works, I do not know
fn read_lines<P>(filename : P) -> io::Result<io::Lines<io::BufReader<File>>> 
where P: AsRef<Path>,
{
    let file = File::open(filename)?;
    Ok(io::BufReader::new(file).lines())
}

fn make_camp(line : String) -> Camp
{    
    let pair : Vec<&str> = line.split(',').collect();

    let s1 : Vec<&str> = pair[0].split('-').collect();
    let s2 : Vec<&str> = pair[1].split('-').collect();

    let camp = Camp
    {
        p1s : s1[0].parse().unwrap(),
        p1e : s1[1].parse().unwrap(),   
        p2s : s2[0].parse().unwrap(),   
        p2e : s2[1].parse().unwrap(),   
    };
    return camp;
}

fn check_overlap(camp : &Camp) -> bool
{
    let mut within = -1;

    //.2345678.  2-8
    //..34567..  3-7 
    //2 <= 3 && 7 <= 8

    if camp.p1s <= camp.p2s && camp.p2e <= camp.p1e
    {
        within = 1;
    }
    if camp.p2s <= camp.p1s && camp.p1e <= camp.p2e
    {
        within = 2;
    }
    //check if there isn't full overlap
    return within != -1;
}

fn check_partial_overlap(camp : &Camp) -> bool
{
    return camp.p1s <= camp.p2s && camp.p2s <= camp.p1e ||
           camp.p2s <= camp.p1s && camp.p1s <= camp.p2e;
}

fn main()
{
    //Pull data from file and write it to camp vectors
    let mut camps : Vec<Camp> = vec![];
    let mut total :i32 = 0;
    if let Ok(lines) = read_lines("./input")
    {
        for line in lines
        {
            if let Ok(ip) = line
            {
                camps.push(make_camp(ip));
            }
        }
    }
    for c in &camps
    {
        if check_overlap(c)
        {
            total += 1;
        }
    }
    println!("{}", total);

    total = 0;

    for c in &camps
    {
        if check_partial_overlap(c)
        {
            total += 1;
        }
    }
    println!("{}", total)
}   
