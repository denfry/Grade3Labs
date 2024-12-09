using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Globalization;
using System.Linq;
using System.Windows;
using System.Windows.Data;
using System.Windows.Media;

namespace Algos2Lab
{
    public partial class MainWindow : Window
    {
        private readonly List<int> _setA = new List<int> { 1, 2, 3, 4, 9 };

        public MainWindow()
        {
            InitializeComponent();
            DisplayRelation();
            DisplayProperties();
        }

        private void DisplayRelation()
        {
            var data = new ObservableCollection<RelationRow>();

            foreach (var b in _setA)
            {
                var row = new RelationRow
                {
                    B = b,
                    A1 = $"(1, {b})",
                    A1Highlight = (1 == b * b),
                    A2 = $"(2, {b})",
                    A2Highlight = (2 == b * b),
                    A3 = $"(3, {b})",
                    A3Highlight = (3 == b * b),
                    A4 = $"(4, {b})",
                    A4Highlight = (4 == b * b),
                    A9 = $"(9, {b})",
                    A9Highlight = (9 == b * b)
                };

                data.Add(row);
            }

            RelationDataGrid.ItemsSource = data;
        }

        private void DisplayProperties()
        {
            var relation = new List<Tuple<int, int>>();
            foreach (var a in _setA)
            {
                foreach (var b in _setA)
                {
                    if (a == b * b)
                    {
                        relation.Add(Tuple.Create(a, b));
                    }
                }
            }

            bool reflexive = _setA.All(a => relation.Contains(Tuple.Create(a, a)));

            bool antiReflexive = _setA.All(a => !relation.Contains(Tuple.Create(a, a)));

            bool symmetric = relation.All(pair => relation.Contains(Tuple.Create(pair.Item2, pair.Item1)));

            bool antiSymmetric = true;
            foreach (var pair in relation)
            {
                if (relation.Contains(Tuple.Create(pair.Item2, pair.Item1)) && pair.Item1 != pair.Item2)
                {
                    antiSymmetric = false;
                    break;
                }
            }

            bool transitive = true;
            foreach (var pair1 in relation)
            {
                foreach (var pair2 in relation)
                {
                    if (pair1.Item2 == pair2.Item1 && !relation.Contains(Tuple.Create(pair1.Item1, pair2.Item2)))
                    {
                        transitive = false;
                        goto Exit;
                    }
                }
            }
        Exit:
            label1.Content = $"Рефлексивное: {reflexive}\n" +
                             $"Антирефлексивное: {antiReflexive}\n" +
                             $"Симметричное: {symmetric}\n" +
                             $"Антисимметричное: {antiSymmetric}\n" +
                             $"Транзитивное: {transitive}";
        }

        public class RelationRow
        {
            public int B { get; set; }
            public string A1 { get; set; }
            public bool A1Highlight { get; set; }
            public string A2 { get; set; }
            public bool A2Highlight { get; set; }
            public string A3 { get; set; }
            public bool A3Highlight { get; set; }
            public string A4 { get; set; }
            public bool A4Highlight { get; set; }
            public string A9 { get; set; }
            public bool A9Highlight { get; set; }
        }
    }
    public class BackgroundConverter : IValueConverter
    {
        public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
        {
            if (value is bool isHighlighted)
            {
                return isHighlighted ? Brushes.LightGreen : Brushes.White;
            }
            return Brushes.White;
        }

        public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
        {
            throw new NotImplementedException();
        }
    }
}