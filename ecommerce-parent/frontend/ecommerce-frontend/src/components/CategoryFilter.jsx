function CategoryFilter({ categories, selectedCategory, onChange }) {
  return (
    <div className="filter-box">
      <label>Select Category: </label>
      <select
        value={selectedCategory}
        onChange={(e) => onChange(e.target.value)}
      >
        <option value="">All</option>
        {categories.map((category) => (
          <option key={category.id} value={category.name}>
            {category.name}
          </option>
        ))}
      </select>
    </div>
  )
}

export default CategoryFilter