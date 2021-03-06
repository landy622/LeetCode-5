## String
### Tricks
- One time extra: Some questions require one extra time execution at the end of the iteration. One way to to add additional unharmful element. [Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/description/)

### [Knuth�Morris�Pratt algorithm](../../../com/algorithm/KnuthMorrisPratt.java)
- First build a longest suffix-prefix table (**lsp**) which recording the next position of the matched character with current one (previously matched character's index + 1). 
- Then initialize two pointers, **s** for the searched and **t** for the pattern. Compare their corresponding characters and if they are matched, both move forward. Otherwise, using the **lsp** table, **t** keeps setting to the previous one (**t** - 1, still matched character)'s next position until it becomes 0 or its character matches current one.
Hard: [Repeated Substring Pattern](https://leetcode.com/problems/repeated-substring-pattern/description/)

### Questions
- [Longest Word in Dictionary through Deleting](https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/description/)

Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

__Dictionary word matching__ question: The idea is using an index and skip some characters if necessary.