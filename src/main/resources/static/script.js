function renderData(data) {
    const dataContainer = document.getElementById('dataContainer');

    // 데이터가 있는지 확인
    if (data && data.length > 0) {
        data.forEach(item => {
            // 데이터를 화면에 추가
            const dataItem = document.createElement('div');
            dataItem.textContent = `${item.id}: ${item.name}`; // 데이터 형식에 맞게 조정
            dataContainer.appendChild(dataItem);
        });
    } else {
        // 데이터가 없는 경우에 대한 처리
        dataContainer.textContent = 'No data available.';
    }
}

// 서버로부터 데이터를 가져와서 렌더링
fetch('http://localhost:8080/api/sample') // 실제 API URL로 대체해야 합니다.
    .then(response => response.json())
    .then(data => renderData(data))
    .catch(error => {
        console.error('Error fetching data:', error);
        const dataContainer = document.getElementById('dataContainer');
        dataContainer.textContent = 'Error fetching data.';
    });