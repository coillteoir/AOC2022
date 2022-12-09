import Data.List

isUnique :: [Char] -> Bool
isUnique quad = quad == (nub quad)
--to get part 1 just swap 14 with 4 :D
findMarker :: [Char] -> Int
findMarker stream = if isUnique (take 14 stream) 
                    then 14 
                    else 1 + findMarker (tail stream)

main = do 
        contents <- readFile "input"
        print $ findMarker contents
