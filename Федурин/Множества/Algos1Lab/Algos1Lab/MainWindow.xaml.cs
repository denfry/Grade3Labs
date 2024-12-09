using System.Collections.Generic;
using System.Linq;
using System.Windows;


namespace Algos1Lab
{
    /// <summary>
    /// Логика взаимодействия для MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            InitializeSets();
            DisplaySets();
        }

        private HashSet<int> _setA, _setB, _setC, _universalSet;

        private void _1_Click(object sender, RoutedEventArgs e)
        {
            var result = GetNotA();
            TextBox1.Text = $"¬A = {{{string.Join(", ", result)}}}";
        }

        private List<int> GetNotA()
        {
            var complementA = new HashSet<int>(_universalSet);
            complementA.ExceptWith(_setA);
            var sortedResult = complementA.ToList();
            sortedResult.Sort();
            return sortedResult;
        }

        private void _2_Click(object sender, RoutedEventArgs e)
        {
            var result = GetNotB();
            TextBox2.Text = $"¬B = {{{string.Join(", ", result)}}}";
        }

        private List<int> GetNotB()
        {
            var complementB = new HashSet<int>(_universalSet);
            complementB.ExceptWith(_setB);
            var sortedResult = complementB.ToList();
            sortedResult.Sort();
            return sortedResult;
        }

        private void _3_Click(object sender, RoutedEventArgs e)
        {
            var result = GetNotC();
            TextBox3.Text = $"¬C = {{{string.Join(", ", result)}}}";
        }

        private List<int> GetNotC()
        {
            var complementC = new HashSet<int>(_universalSet);
            complementC.ExceptWith(_setC);
            var sortedResult = complementC.ToList();
            sortedResult.Sort();
            return sortedResult;
        }

        private void _4_Click(object sender, RoutedEventArgs e)
        {
            var result = GetNotAIntersectB();
            TextBox4.Text = $"¬A ∩ B = {{{string.Join(", ", result)}}}";
        }

        private List<int> GetNotAIntersectB()
        {
            var notA = GetNotA();
            var intersection = new HashSet<int>(notA);
            intersection.IntersectWith(_setB);
            var sortedResult = intersection.ToList();
            sortedResult.Sort();
            return sortedResult;
        }

        private void _5_Click(object sender, RoutedEventArgs e)
        {
            var result = GetAIntersectNotC();
            TextBox5.Text = $"A ∩ ¬C = {{{string.Join(", ", result)}}}";
        }

        private List<int> GetAIntersectNotC()
        {
            var notC = GetNotC();
            var intersection = new HashSet<int>(_setA);
            intersection.IntersectWith(notC);
            var sortedResult = intersection.ToList();
            sortedResult.Sort();
            return sortedResult;
        }

        private void _6_Click(object sender, RoutedEventArgs e)
        {
            var result = GetNotBIntersectC();
            TextBox6.Text = $"¬B ∩ C = {{{string.Join(", ", result)}}}";
        }

        private List<int> GetNotBIntersectC()
        {
            var notB = GetNotB();
            var intersection = new HashSet<int>(notB);
            intersection.IntersectWith(_setC);
            var sortedResult = intersection.ToList();
            sortedResult.Sort();
            return sortedResult;
        }

        private void _7_Click(object sender, RoutedEventArgs e)
        {
            var result = GetNotAIntersectBOrAIntersectNotC();
            TextBox7.Text = $"¬A ∩ B ∪ A ∩ ¬C = {{{string.Join(", ", result)}}}";
        }

        private List<int> GetNotAIntersectBOrAIntersectNotC()
        {
            var part1 = GetNotAIntersectB();
            var part2 = GetAIntersectNotC();
            var unionResult = new HashSet<int>(part1);
            unionResult.UnionWith(part2);
            var sortedResult = unionResult.ToList();
            sortedResult.Sort();
            return sortedResult;
        }

        private void _8_Click(object sender, RoutedEventArgs e)
        {
            var result = GetFinalUnion();
            TextBox8.Text = $"¬A ∩ B ∪ A ∩ ¬C ∪ ¬B ∩ C = {{{string.Join(", ", result)}}}";
        }

        private List<int> GetFinalUnion()
        {
            var part1 = GetNotAIntersectB();
            var part2 = GetAIntersectNotC();
            var part3 = GetNotBIntersectC();
            var unionResult = new HashSet<int>(part1);
            unionResult.UnionWith(part2);
            unionResult.UnionWith(part3);
            var sortedResult = unionResult.ToList();
            sortedResult.Sort();
            return sortedResult;
        }

        private void InitializeSets()
        {
            _setA = new HashSet<int> { 191, 194, 197, 200, 203 };
            _setB = new HashSet<int> { 192, 195, 191, 201, 204 };
            _setC = new HashSet<int> { 193, 196, 199, 203, 205 };

            _universalSet = new HashSet<int>(_setA);
            _universalSet.UnionWith(_setB);
            _universalSet.UnionWith(_setC);
        }

        private void DisplaySets()
        {
            TextBoxSetA.Text = $"A = {{ {string.Join(", ", _setA)} }}";
            TextBoxSetB.Text = $"B = {{ {string.Join(", ", _setB)} }}";
            TextBoxSetC.Text = $"C = {{ {string.Join(", ", _setC)} }}";
            TextBoxUniversal.Text = $"Universal = {{ {string.Join(", ", _universalSet)} }}";
        }
    }
}
