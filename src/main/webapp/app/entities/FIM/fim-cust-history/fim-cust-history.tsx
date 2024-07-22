import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './fim-cust-history.reducer';

export const FimCustHistory = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const fimCustHistoryList = useAppSelector(state => state.fim.fimCustHistory.entities);
  const loading = useAppSelector(state => state.fim.fimCustHistory.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="fim-cust-history-heading" data-cy="FimCustHistoryHeading">
        <Translate contentKey="fimApp.fimFimCustHistory.home.title">Fim Cust Histories</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="fimApp.fimFimCustHistory.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/fim/fim-cust-history/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="fimApp.fimFimCustHistory.home.createLabel">Create new Fim Cust History</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {fimCustHistoryList && fimCustHistoryList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="fimApp.fimFimCustHistory.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('custId')}>
                  <Translate contentKey="fimApp.fimFimCustHistory.custId">Cust Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('custId')} />
                </th>
                <th className="hand" onClick={sort('historyTs')}>
                  <Translate contentKey="fimApp.fimFimCustHistory.historyTs">History Ts</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('historyTs')} />
                </th>
                <th className="hand" onClick={sort('clientId')}>
                  <Translate contentKey="fimApp.fimFimCustHistory.clientId">Client Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('clientId')} />
                </th>
                <th className="hand" onClick={sort('idType')}>
                  <Translate contentKey="fimApp.fimFimCustHistory.idType">Id Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('idType')} />
                </th>
                <th className="hand" onClick={sort('ctryCode')}>
                  <Translate contentKey="fimApp.fimFimCustHistory.ctryCode">Ctry Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('ctryCode')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="fimApp.fimFimCustHistory.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdTs')}>
                  <Translate contentKey="fimApp.fimFimCustHistory.createdTs">Created Ts</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdTs')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="fimApp.fimFimCustHistory.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedTs')}>
                  <Translate contentKey="fimApp.fimFimCustHistory.updatedTs">Updated Ts</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedTs')} />
                </th>
                <th className="hand" onClick={sort('recordStatus')}>
                  <Translate contentKey="fimApp.fimFimCustHistory.recordStatus">Record Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('recordStatus')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {fimCustHistoryList.map((fimCustHistory, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/fim/fim-cust-history/${fimCustHistory.id}`} color="link" size="sm">
                      {fimCustHistory.id}
                    </Button>
                  </td>
                  <td>{fimCustHistory.custId}</td>
                  <td>
                    {fimCustHistory.historyTs ? <TextFormat type="date" value={fimCustHistory.historyTs} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{fimCustHistory.clientId}</td>
                  <td>{fimCustHistory.idType}</td>
                  <td>{fimCustHistory.ctryCode}</td>
                  <td>{fimCustHistory.createdBy}</td>
                  <td>
                    {fimCustHistory.createdTs ? <TextFormat type="date" value={fimCustHistory.createdTs} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{fimCustHistory.updatedBy}</td>
                  <td>
                    {fimCustHistory.updatedTs ? <TextFormat type="date" value={fimCustHistory.updatedTs} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{fimCustHistory.recordStatus}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/fim/fim-cust-history/${fimCustHistory.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/fim/fim-cust-history/${fimCustHistory.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/fim/fim-cust-history/${fimCustHistory.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="fimApp.fimFimCustHistory.home.notFound">No Fim Cust Histories found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default FimCustHistory;
